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
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.item.data.DataType;

/**
 * @author Blair Butterworth
 */
public class TestItemGroups
{
    public static ItemGroup newItemGroup(String id){
        return newItemGroup(new TextIdentifier(id), DataType.Player);
    }

    public static ItemGroup newItemGroup(Identifier id, Identifier type){
        ItemGroup group = new ItemGroup();
        group.setIdentifier(id);
        group.setType(type);
        return group;
    }
}
