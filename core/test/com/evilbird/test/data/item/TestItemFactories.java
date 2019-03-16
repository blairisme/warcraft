/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.data.item;

import com.evilbird.engine.item.ItemFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestItemFactories
{
    private TestItemFactories() {
    }

    public static ItemFactory newItemFactory() {
        ItemFactory itemFactory = mock(ItemFactory.class);
        when(itemFactory.newItem(any())).thenAnswer((invocation) -> TestItems.newItem("item"));
        return itemFactory;
    }
}
