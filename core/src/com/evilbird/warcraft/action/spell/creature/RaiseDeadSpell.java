/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.creature;

import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.warcraft.action.common.create.CreateEvents;
import com.evilbird.warcraft.object.common.spell.Spell;
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.Combatant;

import javax.inject.Inject;
import java.util.Collection;

import static com.evilbird.engine.common.collection.CollectionUtils.filter;
import static com.evilbird.engine.common.collection.CollectionUtils.restrictSize;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;

/**
 * A spell that converts nearby corpses into skeleton warriors that fight for
 * the player the caster belongs to.
 *
 * @author Blair Butterworth
 */
public class RaiseDeadSpell extends CreatureSpellAction
{
    private static final int SEARCH_RADIUS = tiles(5);

    @Inject
    public RaiseDeadSpell(GameObjectFactory factory, CreateEvents events) {
        super(Spell.RaiseDead, EffectType.Spell, UnitType.Skeleton, factory, events, null);
    }

    @Override
    protected Combatant addCreature() {
        for (GameObject corpse: nearbyCorpses()) {
            GameObject creature = super.addCreature();
            creature.setPosition(corpse.getPosition());
        }
        return null;
    }

    private Collection<GameObject> nearbyCorpses() {
        GameObject caster = getSubject();
        GameObjectContainer state = caster.getRoot();
        GameObjectGraph graph = state.getSpatialGraph();
        Collection<GameObject> nearby = graph.getOccupants(caster, SEARCH_RADIUS);
        Collection<GameObject> corpses = filter(nearby, this::isDeadCombatant);
        return restrictSize(corpses, Spell.RaiseDead.getEffectValue());
    }

    private boolean isDeadCombatant(GameObject gameObject) {
        if (gameObject instanceof Combatant) {
            Combatant combatant = (Combatant) gameObject;
            return combatant.isDead();
        }
        return false;
    }
}
