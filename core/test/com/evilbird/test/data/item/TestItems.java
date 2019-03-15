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
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemBasic;
import com.evilbird.warcraft.item.unit.UnitType;

public class TestItems
{
    public static Item newItem(String id) {
        return newItem(new TextIdentifier(id), UnitType.Footman);
    }

    public static Item newItem(Identifier identifier, Identifier type) {
        Item item = new ItemBasic();
        item.setIdentifier(identifier);
        item.setType(type);
        item.setPosition(12, 34);
        item.setSize(56, 78);
        return item;
    }
}
