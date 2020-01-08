/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.data.item;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.data.player.PlayerStatistic;
import com.evilbird.warcraft.object.data.player.PlayerType;
import com.evilbird.warcraft.object.unit.UnitType;

import static com.evilbird.test.data.item.TestBuildings.newTestBuilding;
import static com.evilbird.test.data.item.TestCombatants.newTestCombatant;
import static com.evilbird.test.data.item.TestItemRoots.newTestRoot;

public class TestPlayers
{
    private TestPlayers() {
    }

    public static Player newTestPlayer(String id) {
        return newTestPlayer(new TextIdentifier(id));
    }

    public static Player newTestPlayer(Identifier identifier) {
        return newTestPlayer(identifier, newTestRoot("root"));
    }

    public static Player newTestPlayer(String id, GameObjectContainer root) {
        return newTestPlayer(new TextIdentifier(id), root);
    }

    public static Player newTestPlayer(Identifier identifier, GameObjectContainer root) {
        Player player = new Player();
        player.setIdentifier(identifier);
        player.setType(PlayerType.Corporeal);
        player.setResource(ResourceType.Gold, 123);
        player.setResource(ResourceType.Wood, 456);
        player.setStatistic(PlayerStatistic.Kills, 4);
        player.setStatistic(PlayerStatistic.Buildings, 10);
        player.addObject(newTestCombatant(new TextIdentifier("footman"), UnitType.Footman, root, player));
        player.addObject(newTestBuilding(new TextIdentifier("barracks"), UnitType.Barracks, root, player));
        player.setRoot(root);
        player.setParent(root.getBaseGroup());
        return player;
    }
}
