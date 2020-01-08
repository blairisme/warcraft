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
import com.evilbird.engine.object.BasicGameObject;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.unit.UnitType;

public class TestItems
{
    private TestItems() {
    }

    public static GameObject newItem(String id) {
        return newItem(new TextIdentifier(id), UnitType.Peasant);
    }

    public static GameObject newItem(Identifier identifier, Identifier type) {
        GameObject gameObject = new BasicGameObject();
        gameObject.setIdentifier(identifier);
        gameObject.setType(type);
        gameObject.setPosition(12, 34);
        gameObject.setSize(56, 78);
        return gameObject;
    }
}
