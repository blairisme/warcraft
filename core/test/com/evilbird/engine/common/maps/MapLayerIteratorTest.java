/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.maps;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate logic in the {@link MapLayerIterator}
 * class.
 *
 * @author Blair Butterworth
 */
public class MapLayerIteratorTest
{
    private Cell cell1;
    private Cell cell2;
    private Cell cell3;
    private Cell cell4;
    private TiledMapTileLayer layer;
    private MapLayerIterator iterator;

    @Before
    public void setup() {
        cell1 = Mockito.mock(Cell.class);
        cell2 = Mockito.mock(Cell.class);
        cell3 = Mockito.mock(Cell.class);
        cell4 = Mockito.mock(Cell.class);
        layer = Mockito.mock(TiledMapTileLayer.class);
        Mockito.when(layer.getWidth()).thenReturn(3);
        Mockito.when(layer.getHeight()).thenReturn(3);
        Mockito.when(layer.getCell(0, 0)).thenReturn(cell1);
        Mockito.when(layer.getCell(2, 0)).thenReturn(cell2);
        Mockito.when(layer.getCell(1, 2)).thenReturn(cell3);
        Mockito.when(layer.getCell(2, 2)).thenReturn(cell4);
        iterator = new MapLayerIterator(layer);
    }

    @Test
    public void iterateTest() {
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(cell1, iterator.next().getCell());

        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(cell2, iterator.next().getCell());

        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(cell3, iterator.next().getCell());

        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(cell4, iterator.next().getCell());

        Assert.assertFalse(iterator.hasNext());
        Assert.assertNull(iterator.next());
    }

    @Test
    public void oneRowTest() {
        Mockito.when(layer.getHeight()).thenReturn(1);
        iterator = new MapLayerIterator(layer);

        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(cell1, iterator.next().getCell());

        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(cell2, iterator.next().getCell());

        Assert.assertFalse(iterator.hasNext());
        Assert.assertNull(iterator.next());
    }

    @Test
    public void noCellsTest() {
        Mockito.when(layer.getCell(0, 0)).thenReturn(null);
        Mockito.when(layer.getCell(2, 0)).thenReturn(null);
        Mockito.when(layer.getCell(1, 2)).thenReturn(null);
        Mockito.when(layer.getCell(2, 2)).thenReturn(null);
        iterator = new MapLayerIterator(layer);

        Assert.assertFalse(iterator.hasNext());
        Assert.assertNull(iterator.next());
    }

    @Test
    public void zeroSizeTest() {
        Mockito.when(layer.getWidth()).thenReturn(0);
        Mockito.when(layer.getHeight()).thenReturn(0);
        iterator = new MapLayerIterator(layer);

        Assert.assertFalse(iterator.hasNext());
        Assert.assertNull(iterator.next());
    }
}