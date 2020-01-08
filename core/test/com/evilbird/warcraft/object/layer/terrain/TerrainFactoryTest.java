/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.terrain;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.object.layer.LayerIdentifier;
import com.evilbird.warcraft.object.layer.LayerType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link TerrainFactory} class.
 *
 * @author Blair Butterworth
 */
public class TerrainFactoryTest extends GameTestCase
{
    private TerrainFactory factory;

    @Before
    public void setup() {
        factory = new TerrainFactory();
    }

    @Test
    public void getTest() {
        TiledMapTileLayer layer = new TiledMapTileLayer(1024, 1024, 32, 32);
        LayerIdentifier identifier = new LayerIdentifier("file", "Map", layer);
        Terrain terrain = factory.get(identifier);

        Assert.assertNotNull(terrain);
        Assert.assertEquals(LayerType.Map, terrain.getType());
    }
}