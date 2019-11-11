/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.data.item;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.BasicGameObject;
import com.evilbird.warcraft.item.unit.UnitType;

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
