/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.wall;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameContext;
import com.evilbird.test.testcase.GameFactoryTestCase;
import com.evilbird.warcraft.item.layer.LayerGroupStyle;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import com.evilbird.warcraft.item.layer.LayerType;
import com.evilbird.warcraft.state.WarcraftContext;
import org.junit.Assert;
import org.junit.Test;

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
 * Instances of this unit test validate the {@link WallFactory} class.
 *
 * @author Blair Butterworth
 */
public class WallFactoryTest extends GameFactoryTestCase<WallFactory>
{
    @Override
    protected WallFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new WallFactory(assets);
    }

    @Override
    protected Collection<GameContext> getLoadContexts() {
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
        LayerIdentifier identifier = new LayerIdentifier("file", "Wall", layer);
        Wall forest = (Wall)factory.get(identifier);

        Assert.assertNotNull(forest);
        Assert.assertEquals(LayerType.Wall, forest.getType());
        Assert.assertTrue(forest.getSkin().has("default", LayerGroupStyle.class));
    }

    @Override
    protected Collection<Identifier> getProductIdentifiers() {
        return Collections.singleton(LayerType.Wall);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return null;
    }
}