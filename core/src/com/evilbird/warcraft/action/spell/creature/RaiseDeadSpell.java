/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.creature;

import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.warcraft.action.common.create.CreateEvents;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;
import java.util.Collection;

import static com.evilbird.engine.common.collection.CollectionUtils.filter;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;

/**
 * A spell that converts nearby corpses into skeleton warriors that fight for
 * the player the caster belongs to.
 *
 * @author Blair Butterworth
 */
public class RaiseDeadSpell extends CreatureSpellAction
{
    private static final int RADIUS = tiles(5);

    @Inject
    public RaiseDeadSpell(ItemFactory factory, CreateEvents events) {
        super(Spell.RaiseDead, EffectType.Spell, UnitType.Skeleton, factory, events, null);
    }

    @Override
    protected Combatant addCreature() {
        for (Item corpse: nearbyCorpses()) {
            Combatant creature = super.addCreature();
            creature.setPosition(corpse.getPosition());
        }
        return null;
    }

    private Collection<Item> nearbyCorpses() {
        Item caster = getItem();
        ItemRoot state = caster.getRoot();
        ItemGraph graph = state.getSpatialGraph();
        Collection<Item> corpses = graph.getOccupants(caster, RADIUS);
        return filter(corpses, this::isDeadCombatant);
    }

    private boolean isDeadCombatant(Item item) {
        if (item instanceof Combatant) {
            Combatant combatant = (Combatant)item;
            return combatant.getHealth() == 0;
        }
        return false;
    }
}
