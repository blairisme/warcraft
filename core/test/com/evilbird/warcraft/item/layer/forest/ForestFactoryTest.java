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
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.test.testcase.GameFactoryTestCase;
import com.evilbird.warcraft.common.WarcraftContext;
import com.evilbird.warcraft.item.layer.LayerGroupStyle;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import com.evilbird.warcraft.item.layer.LayerType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.evilbird.warcraft.common.WarcraftAssetSet.Summer;
import static com.evilbird.warcraft.common.WarcraftAssetSet.Swamp;
import static com.evilbird.warcraft.common.WarcraftAssetSet.Winter;
import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;

/**
 * Instances of this unit test validate the {@link ForestFactory} class.
 *
 * @author Blair Butterworth
 */
public class ForestFactoryTest extends GameFactoryTestCase<ForestFactory>
{
    @Override
    protected ForestFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new ForestFactory(assets);
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
        LayerIdentifier identifier = new LayerIdentifier("file", "Forest", layer);
        Forest forest = (Forest)factory.get(identifier);

        Assert.assertNotNull(forest);
        Assert.assertEquals(LayerType.Forest, forest.getType());
        Assert.assertTrue(forest.getSkin().has("default", LayerGroupStyle.class));
    }

    @Override
    protected Collection<Identifier> getValueTypes() {
        return Collections.singleton(LayerType.Forest);
    }

    @Override
    protected Map<String, Object> getValueProperties() {
        return null;
    }
}