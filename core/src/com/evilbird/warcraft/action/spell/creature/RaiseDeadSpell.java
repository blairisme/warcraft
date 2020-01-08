/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell.creature;

import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.warcraft.action.common.create.CreateEvents;
import com.evilbird.warcraft.data.spell.Spell;
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
    public RaiseDeadSpell(CreateEvents events, GameObjectFactory factory) {
        super(factory, events, null);
        setSpell(Spell.RaiseDead);
        setEffect(EffectType.Spell);
        setProduct(UnitType.Skeleton);
    }

    @Override
    protected void initialize() {
        super.initialize();
        GameObject caster = getSubject();
        for (GameObject corpse: nearbyCorpses()) {
            addCreature(caster, corpse);
        }
    }

    @Override
    protected GameObject newCreature(GameObject caster, GameObject target) {
        GameObject creature = super.newCreature(caster, target);
        creature.setPosition(target.getPosition());
        return creature;
    }

    private Collection<GameObject> nearbyCorpses() {
        GameObject caster = getSubject();
        GameObjectContainer state = caster.getRoot();
        GameObjectGraph graph = state.getSpatialGraph();
        Collection<GameObject> nearby = graph.getOccupants(caster, SEARCH_RADIUS);
        Collection<GameObject> corpses = filter(nearby, this::isDeadCombatant);
        return restrictSize(corpses, (int)Spell.RaiseDead.getEffectValue());
    }

    private boolean isDeadCombatant(GameObject gameObject) {
        if (gameObject instanceof Combatant) {
            Combatant combatant = (Combatant) gameObject;
            return combatant.isDead();
        }
        return false;
    }
}
