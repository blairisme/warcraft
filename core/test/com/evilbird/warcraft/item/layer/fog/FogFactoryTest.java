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
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.test.testcase.GameFactoryTestCase;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import com.evilbird.warcraft.item.layer.LayerType;
import com.evilbird.warcraft.state.WarcraftContext;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;
import static com.evilbird.warcraft.common.WarcraftSeason.Summer;
import static com.evilbird.warcraft.common.WarcraftSeason.Swamp;
import static com.evilbird.warcraft.common.WarcraftSeason.Winter;

/**
 * Instances of this unit test validate the {@link FogFactory} class.
 *
 * @author Blair Butterworth
 */
public class FogFactoryTest extends GameFactoryTestCase<FogFactory>
{
    @Override
    protected FogFactory newFactory(DeviceDisplay display, AssetManager assets) {
        EventQueue queue = Mockito.mock(EventQueue.class);
        return new FogFactory(assets, queue);
    }

    @Override
    protected Collection<Identifier> getLoadContexts() {
        return Arrays.asList(
            new WarcraftContext(Human, Summer),
            new WarcraftContext(Human, Swamp),
            new WarcraftContext(Human, Winter),
            new WarcraftContext(Orc, Summer),
            new WarcraftContext(Orc, Swamp),
            new WarcraftContext(Orc, Winter));
    }

    @Test
    public void getTest() {
        factory.load(new WarcraftContext(Human, Winter));
        assets.finishLoading();

        TiledMapTileLayer layer = new TiledMapTileLayer(1024, 1024, 32, 32);
        LayerIdentifier identifier = new LayerIdentifier("file", "OpaqueFog", layer);
        Fog fog = (Fog)factory.get(identifier);

        Assert.assertNotNull(fog);
        Assert.assertEquals(LayerType.OpaqueFog, fog.getType());
        Assert.assertTrue(fog.getSkin().has("default", FogStyle.class));
    }

    @Override
    protected Collection<Identifier> getValueTypes() {
        return Collections.singleton(LayerType.OpaqueFog);
    }

    @Override
    protected Map<String, Object> getValueProperties() {
        return null;
    }
}