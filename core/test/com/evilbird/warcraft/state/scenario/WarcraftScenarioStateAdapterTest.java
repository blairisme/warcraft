/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.scenario;

import com.badlogic.gdx.files.FileHandle;
import com.evilbird.engine.common.maps.TiledMapLoader;
import com.evilbird.engine.device.Device;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.utils.JsonSerializerContext;
import com.evilbird.test.utils.TestFileHandleResolver;
import com.evilbird.warcraft.state.map.WarcraftLevel;
import com.evilbird.warcraft.state.map.WarcraftLevelLoader;
import com.google.gson.JsonElement;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.evilbird.test.data.device.TestDevices.newTestDevice;

/**
 * Instances of this unit test validate the {@link WarcraftScenarioAdapter} class.
 *
 * @author Blair Butterworth
 */
public class WarcraftScenarioStateAdapterTest extends GameTestCase
{
    private Device device;
    private WarcraftScenarioAdapter adapter;
    private com.evilbird.warcraft.state.map.WarcraftLevelLoader assetLoader;
    private TiledMapLoader mapLoader;
    private TestFileHandleResolver resolver;

    @Before
    public void setup() {
        super.setup();
        device = newTestDevice();
        resolver = new TestFileHandleResolver();
        mapLoader = new TiledMapLoader(resolver);
        assetLoader = new WarcraftLevelLoader(itemFactory, mapLoader);
        adapter = new WarcraftScenarioAdapter(device, itemFactory, behaviourFactory, assetLoader);
    }

    @Test
    public void deserializeSaveTest() {
        FileHandle handle = resolver.resolve("/warcraft/state/save.json");

        JsonReader reader = new JsonReader(handle.reader());
        JsonElement element = Streams.parse(reader);
        JsonSerializerContext context = new JsonSerializerContext();

        WarcraftScenarioState state = adapter.deserialize(element, WarcraftScenarioState.class, context);
        Assert.assertNotNull(state);
        Assert.assertNotNull(state.getWorld());
        Assert.assertNotNull(state.getHud());
        Assert.assertNotNull(state.getBehaviour());
    }

    @Test
    public void deserializeAssetTest() {
        resolver.respondWith(WarcraftLevel.Human1.getFilePath(), "/warcraft/state/level.tmx");
        FileHandle handle = resolver.resolve("/warcraft/state/level.json");

        JsonReader reader = new JsonReader(handle.reader());
        JsonElement element = Streams.parse(reader);
        JsonSerializerContext context = new JsonSerializerContext();

        WarcraftScenarioState state = adapter.deserialize(element, WarcraftScenarioState.class, context);
        Assert.assertNotNull(state);
        Assert.assertNotNull(state.getWorld());
        Assert.assertNotNull(state.getHud());
        Assert.assertNotNull(state.getBehaviour());
    }
}