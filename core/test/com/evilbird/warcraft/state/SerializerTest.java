/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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