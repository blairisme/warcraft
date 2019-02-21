/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.common.serialization.JsonSerializer;
import com.evilbird.engine.common.serialization.Serializer;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * Instances of this unit test validate the {@link Item} class.
 *
 * @author Blair Butterworth
 */
public class ItemTest
{
    @Test
    public void serializeTest() {
        AssetManager assets = mock(AssetManager.class);

        Item item = new Item();
        item.setPosition(12, 34);
        item.setSize(56, 78);

        Serializer serializer = new JsonSerializer(assets, null);
        String serialized = serializer.serialize(item, Item.class);

        Item deserialized = serializer.deserialize(serialized, Item.class);
        Objects.equals(serialized, deserialized);
    }
}
