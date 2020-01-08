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
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.resource.Resource;

import static com.evilbird.test.data.item.TestPlayers.newTestPlayer;
import static com.evilbird.test.data.item.TestSkins.newTestSkin;

public class TestResources
{
    private TestResources() {
    }

    public static Resource newTestResource(String id) {
        return newTestResource(new TextIdentifier(id), UnitType.GoldMine);
    }

    public static Resource newTestResource(Identifier identifier, Identifier type) {
        return newTestResource(identifier, type, TestItemRoots.newTestRoot("root"), newTestPlayer("parent"));
    }

    public static Resource newTestResource(Identifier identifier, Identifier type, GameObjectContainer root, Player parent) {
        Resource resource = new Resource(newTestSkin());
        resource.setIdentifier(identifier);
        resource.setType(type);
        resource.setPosition(12, 34);
        resource.setSize(56, 78);
        resource.setParent(parent);
        resource.setRoot(root);
        resource.setResource(ResourceType.Gold, 123);
        resource.setResource(ResourceType.Wood, 456);
        return resource;
    }
}
