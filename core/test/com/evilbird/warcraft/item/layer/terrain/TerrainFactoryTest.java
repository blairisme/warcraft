/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.terrain;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import com.evilbird.warcraft.item.layer.LayerType;
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