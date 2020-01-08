/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.scenario.condition;

import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.events.Event;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.test.data.item.TestBuildings;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.data.item.TestGatherers;
import com.evilbird.test.data.item.TestItemRoots;
import com.evilbird.test.data.item.TestPlayers;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.action.construct.ConstructEvent;
import com.evilbird.warcraft.action.construct.ConstructStatus;
import com.evilbird.warcraft.action.death.RemoveEvent;
import com.evilbird.warcraft.action.produce.ProduceEvent;
import com.evilbird.warcraft.action.produce.ProduceStatus;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.evilbird.warcraft.object.data.player.PlayerIds.Player1;
import static com.evilbird.warcraft.object.data.player.PlayerIds.Player2;
import static com.evilbird.warcraft.object.unit.UnitType.Footman;
import static com.evilbird.warcraft.object.unit.UnitType.TownHall;

/**
 * Instances of this unit test validate the {@link PlayerOwnership} class.
 *
 * @author Blair Butterworth
 */
public class PlayerOwnershipTest extends GameTestCase
{
    private GameObjectContainer root;
    private Player player1;
    private Player player2;
    private Combatant combatant1;
    private Combatant combatant2;
    private Combatant combatant3;

    @Before
    public void setup() {
        root = TestItemRoots.newTestRoot("root");
        player1 = TestPlayers.newTestPlayer(Player1, root);
        player2 = TestPlayers.newTestPlayer(Player2, root);

        root.removeObjects();
        root.addObject(player1);
        root.addObject(player2);

        combatant1 = TestCombatants.newTestCombatant(new TextIdentifier("test1"), Footman, root, player1);
        combatant2 = TestCombatants.newTestCombatant(new TextIdentifier("test2"), Footman, root, player1);
        combatant3 = TestCombatants.newTestCombatant(new TextIdentifier("test3"), Footman, root, player2);

        player1.removeObjects();
        player2.removeObjects();
        player1.addObject(combatant1);
        player1.addObject(combatant2);
        player2.addObject(combatant3);
    }

    @Test
    public void applicableConstructTest() {
        Gatherer builder = TestGatherers.newTestGatherer("builder");
        Building building = TestBuildings.newTestBuilding("building");

        PlayerOwnership condition = PlayerOwnership.playerOwns(Footman, 2);
        condition.initialize(root);

        EventQueue queue = new EventQueue();
        queue.add(new ConstructEvent(building, ConstructStatus.Started));
        Assert.assertFalse(condition.applicable(queue));

        queue.clear();
        queue.add(new ConstructEvent(building, ConstructStatus.Complete));
        Assert.assertTrue(condition.applicable(queue));
    }

    @Test
    public void applicableProduceTest() {
        Building building = TestBuildings.newTestBuilding("building");

        PlayerOwnership condition = PlayerOwnership.playerOwns(Footman, 2);
        condition.initialize(root);

        EventQueue queue = new EventQueue();
        queue.add(new ProduceEvent(building, ProduceStatus.Started));
        Assert.assertFalse(condition.applicable(queue));

        queue.clear();
        queue.add(new ProduceEvent(building, ProduceStatus.Complete));
        Assert.assertTrue(condition.applicable(queue));
    }

    @Test
    public void notApplicableTest() {
        GameObject subject = TestCombatants.newTestCombatant("footman");
        Event event = new RemoveEvent(subject);

        EventQueue queue = new EventQueue();
        queue.add(event);

        PlayerOwnership condition = PlayerOwnership.playerOwns(Footman, 2);
        condition.initialize(root);
        Assert.assertFalse(condition.applicable(queue));
    }

    @Test
    public void evaluateTrueTest() {
        PlayerOwnership condition = PlayerOwnership.playerOwns(Footman, 2);
        condition.initialize(root);
        Assert.assertTrue(condition.evaluate(root));
    }

    @Test
    public void evaluateFalseTest() {
        PlayerOwnership condition = PlayerOwnership.playerOwns(TownHall, 1);
        condition.initialize(root);
        Assert.assertFalse(condition.evaluate(root));
    }
}