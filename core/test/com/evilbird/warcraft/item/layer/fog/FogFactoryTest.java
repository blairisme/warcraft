/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.fog;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.test.data.device.TestAssets;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import com.evilbird.warcraft.item.layer.LayerType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate the {@link FogFactory} class.
 *
 * @author Blair Butterworth
 */
public class FogFactoryTest extends GameTestCase
{
    private FogFactory factory;
    private AssetManager assets;
    private EventQueue events;

    @Before
    public void setup() {
        assets = TestAssets.newAssetManagerMock();
        events = Mockito.mock(EventQueue.class);
        factory = new FogFactory(assets, events);
    }

    @Test
    public void loadTest() {
        factory.load(null);
        Mockito.verify(assets).load(FogFactory.TERRAIN, Texture.class);
    }

    @Test
    public void getTest() {
        TiledMapTileLayer layer = new TiledMapTileLayer(1024, 1024, 32, 32);
        LayerIdentifier identifier = new LayerIdentifier("file", "OpaqueFog", layer);
        Fog fog = factory.get(identifier);

        Assert.assertNotNull(fog);
        Assert.assertEquals(LayerType.OpaqueFog, fog.getType());
        Assert.assertTrue(fog.getSkin().has("default", FogStyle.class));
    }
}