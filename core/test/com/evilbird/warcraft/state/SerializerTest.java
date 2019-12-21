/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.badlogic.gdx.files.FileHandle;
import com.evilbird.test.utils.JsonSerializerContext;
import com.evilbird.warcraft.object.display.UserInterfaceBuilder;
import com.google.gson.JsonElement;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.evilbird.test.data.assets.TestDevices.newTestDevice;

/**
 * Instances of this unit test validate the {@link WarcraftStateSerializer} class.
 *
 * @author Blair Butterworth
 */
public class SerializerTest extends StateTestCase
{
    private UserInterfaceBuilder userInterfaceLoader;
    private WarcraftMusic musicLoader;
    private WarcraftStateSerializer adapter;

    @Before
    public void setup() {
        super.setup();
        device = newTestDevice();
        userInterfaceLoader = new UserInterfaceBuilder(device, objectFactory);
        musicLoader = new WarcraftMusic(device);
        adapter = new WarcraftStateSerializer(userInterfaceLoader, levelLoader, musicLoader, behaviourFactory);
        loadAssets();
    }

    @Test
    public void deserializeSaveTest() {
        FileHandle handle = assetResolver.resolve("/warcraft/state/save.json");

        JsonReader reader = new JsonReader(handle.reader());
        JsonElement element = Streams.parse(reader);
        JsonSerializerContext context = new JsonSerializerContext();

        WarcraftState state = adapter.deserialize(element, WarcraftState.class, context);
        Assert.assertNotNull(state);
        Assert.assertNotNull(state.getWorld());
        Assert.assertNotNull(state.getHud());
        Assert.assertNotNull(state.getBehaviour());
    }

    @Test
    public void deserializeAssetTest() {
        assetResolver.respondWith(WarcraftCampaignLevel.Human1.getFilePath(), "/warcraft/state/level.tmx");
        FileHandle handle = assetResolver.resolve("/warcraft/state/level.json");

        JsonReader reader = new JsonReader(handle.reader());
        JsonElement element = Streams.parse(reader);
        JsonSerializerContext context = new JsonSerializerContext();

        WarcraftState state = adapter.deserialize(element, WarcraftState.class, context);
        Assert.assertNotNull(state);
        Assert.assertNotNull(state.getWorld());
        Assert.assertNotNull(state.getHud());
        Assert.assertNotNull(state.getBehaviour());
    }
}