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
import com.evilbird.engine.game.GameController;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.utils.JsonSerializerContext;
import com.evilbird.test.utils.TestFileHandleResolver;
import com.evilbird.warcraft.item.ui.hud.HudLoader;
import com.evilbird.warcraft.state.map.Level;
import com.evilbird.warcraft.state.map.LevelLoader;
import com.google.gson.JsonElement;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.evilbird.test.data.device.TestDevices.newTestDevice;

/**
 * Instances of this unit test validate the {@link ScenarioAdapter} class.
 *
 * @author Blair Butterworth
 */
public class ScenarioStateAdapterTest extends GameTestCase
{
    private HudLoader hudLoader;
    private GameController controller;
    private ScenarioAdapter adapter;
    private LevelLoader levelLoader;
    private TiledMapLoader mapLoader;
    private TestFileHandleResolver resolver;

    @Before
    public void setup() {
        super.setup();
        device = newTestDevice();
        controller = Mockito.mock(GameController.class);
        resolver = new TestFileHandleResolver();
        hudLoader = new HudLoader(device, itemFactory);
        mapLoader = new TiledMapLoader(resolver);
        levelLoader = new LevelLoader(itemFactory, mapLoader);
        adapter = new ScenarioAdapter(controller, hudLoader, levelLoader, behaviourFactory);
    }

    @Test
    public void deserializeSaveTest() {
        FileHandle handle = resolver.resolve("/warcraft/state/save.json");

        JsonReader reader = new JsonReader(handle.reader());
        JsonElement element = Streams.parse(reader);
        JsonSerializerContext context = new JsonSerializerContext();

        ScenarioState state = adapter.deserialize(element, ScenarioState.class, context);
        Assert.assertNotNull(state);
        Assert.assertNotNull(state.getWorld());
        Assert.assertNotNull(state.getHud());
        Assert.assertNotNull(state.getBehaviour());
    }

    @Test
    public void deserializeAssetTest() {
        resolver.respondWith(Level.Human1.getFilePath(), "/warcraft/state/level.tmx");
        FileHandle handle = resolver.resolve("/warcraft/state/level.json");

        JsonReader reader = new JsonReader(handle.reader());
        JsonElement element = Streams.parse(reader);
        JsonSerializerContext context = new JsonSerializerContext();

        ScenarioState state = adapter.deserialize(element, ScenarioState.class, context);
        Assert.assertNotNull(state);
        Assert.assertNotNull(state.getWorld());
        Assert.assertNotNull(state.getHud());
        Assert.assertNotNull(state.getBehaviour());
    }
}