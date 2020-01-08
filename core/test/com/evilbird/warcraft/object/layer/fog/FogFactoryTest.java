/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.fog;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.game.GameContext;
import com.evilbird.test.testcase.GameFactoryTestCase;
import com.evilbird.warcraft.object.layer.LayerGroupStyle;
import com.evilbird.warcraft.object.layer.LayerIdentifier;
import com.evilbird.warcraft.object.layer.LayerType;
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
        LayerIdentifier identifier = new LayerIdentifier("file", "OpaqueFog", layer);
        Fog fog = (Fog)factory.get(identifier);

        Assert.assertNotNull(fog);
        Assert.assertEquals(LayerType.OpaqueFog, fog.getType());
        Assert.assertTrue(fog.getSkin().has("default", LayerGroupStyle.class));
    }

    @Override
    protected Collection<Identifier> getProductIdentifiers() {
        return Collections.singleton(LayerType.OpaqueFog);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return null;
    }
}