/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ai.attack;

import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.evilbird.engine.common.collection.CollectionUtils.flatten;
import static com.evilbird.warcraft.item.common.query.UnitComparators.combatantsFirst;

/**
 * Represents a mapping between spatial locations and attackers sight-lines,
 * allowing easy retrieval of those potential attackers within range of a given
 * target.
 *
 * @author Blair Butterworth
 */
public class AttackGraph
{
    private ItemGraph graph;
    private Map<ItemNode, Collection<Combatant>> attackers;

    public AttackGraph(ItemGraph graph) {
        this.graph = graph;
        this.attackers = new HashMap<>();
    }

    public Collection<Combatant> getAttackers(ItemNode node) {
        return attackers.getOrDefault(node, Collections.emptyList());
    }

    public Collection<Item> getAttackTargets(Combatant combatant) {
        Collection<ItemNode> nodes = graph.getNodes(combatant.getPosition(), combatant.getSize(), combatant.getSight());
        List<Item> targets = flatten(nodes, ItemNode::getOccupants);
        targets.sort(combatantsFirst());
        return targets;
    }

    public void addAttacker(Combatant combatant) {
        for (ItemNode node: getAttackRangeNodes(combatant)) {
            Collection<Combatant> occupants = Maps.getOrDefault(attackers, node, ArrayList::new);
            occupants.add(combatant);
            attackers.put(node, occupants);
        }
    }

    public void addAttackers(Collection<Combatant> combatants) {
        combatants.forEach(this::addAttacker);
    }

    public void removeAttacker(Combatant combatant) {
        for (Entry<ItemNode, Collection<Combatant>> entry: attackers.entrySet()) {
            entry.getValue().remove(combatant);
        }
    }

    public void updateAttacker(Combatant combatant) {
        removeAttacker(combatant);
        addAttacker(combatant);
    }

    private Collection<ItemNode> getAttackRangeNodes(Combatant combatant) {
        return graph.getNodes(combatant.getPosition(), combatant.getSize(), combatant.getSight());
    }
}
