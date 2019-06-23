/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.query;

import com.evilbird.engine.common.lang.Destroyable;
import com.evilbird.engine.item.Item;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Instances of this unit test validate the {@link UnitOperations} class.
 *
 * @author Blair Butterworth
 */
public class UnitOperationsTest
{
    @Test
    public void isAlive() {
        Destroyable item = mock(Destroyable.class);

        when(item.getHealth()).thenReturn(40f);
        assertTrue(UnitOperations.isAlive(item));

        when(item.getHealth()).thenReturn(0f);
        assertFalse(UnitOperations.isAlive(item));
    }

    @Test
    public void isAliveNonUnit() {
        Item item = mock(Item.class);
        assertFalse(UnitOperations.isAlive(item));
    }

    @Test
    public void isAliveNull() {
        assertFalse(UnitOperations.isAlive(null));
    }
}
