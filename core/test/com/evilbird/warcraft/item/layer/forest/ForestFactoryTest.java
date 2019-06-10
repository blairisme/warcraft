/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.forest;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.evilbird.test.data.device.TestAssets;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.item.layer.LayerGroupStyle;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import com.evilbird.warcraft.item.layer.LayerType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate the {@link ForestFactory} class.
 *
 * @author Blair Butterworth
 */
public class ForestFactoryTest extends GameTestCase
{
    private ForestFactory factory;
    private AssetManager assets;

    @Before
    public void setup() {
        assets = TestAssets.newAssetManagerMock();
        factory = new ForestFactory(assets);
    }

    @Test
    public void loadTest() {
        factory.load();
        Mockito.verify(assets).load(ForestFactory.TERRAIN, Texture.class);
    }

    @Test
    public void getTest() {
        TiledMapTileLayer layer = new TiledMapTileLayer(1024, 1024, 32, 32);
        LayerIdentifier identifier = new LayerIdentifier("file", "Forest", layer);
        Forest forest = factory.get(identifier);

        Assert.assertNotNull(forest);
        Assert.assertEquals(LayerType.Forest, forest.getType());
        Assert.assertTrue(forest.getSkin().has("default", LayerGroupStyle.class));
    }
}