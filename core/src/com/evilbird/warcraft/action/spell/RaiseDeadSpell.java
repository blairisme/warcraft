/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell;

import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;
import java.util.Collection;

import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getPlayer;

/**
 * A spell that converts nearby corpses into skeleton warriors that fight for
 * the player the caster belongs to.
 *
 * @author Blair Butterworth
 */
public class RaiseDeadSpell extends SpellAction
{
    private static final int RADIUS = tiles(5);

    @Inject
    public RaiseDeadSpell(ItemFactory factory) {
        super(Spell.RaiseDead, EffectType.Spell, factory);
    }

    @Override
    protected void initialize() {
        super.initialize();
        Item caster = getItem();
        Player player = getPlayer(caster);
        Collection<Item> corpses = getNearbyCorpses(caster);
        createSkeletons(player, corpses);
    }

    private Collection<Item> getNearbyCorpses(Item caster) {
        ItemRoot state = caster.getRoot();
        ItemGraph graph = state.getSpatialGraph();
        Collection<Item> corpses = graph.getOccupants(caster, RADIUS);
        return CollectionUtils.filter(corpses, this::isDeadCombatant);
    }

    private boolean isDeadCombatant(Item item) {
        if (item instanceof Combatant) {
            Combatant combatant = (Combatant)item;
            return combatant.getHealth() == 0;
        }
        return false;
    }

    private void createSkeletons(Player player, Collection<Item> corpses) {
        for (Item corpse: corpses) {
            Item creature = factory.get(UnitType.Skeleton);
            creature.setPosition(corpse.getPosition());
            player.addItem(creature);
        }
    }
}
