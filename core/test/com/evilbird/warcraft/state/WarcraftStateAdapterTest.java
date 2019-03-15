/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.badlogic.gdx.files.FileHandle;
import com.evilbird.engine.common.maps.TiledMapLoader;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.utils.JsonSerializerContext;
import com.evilbird.test.utils.TestFileHandleResolver;
import com.google.gson.JsonElement;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.evilbird.warcraft.state.WarcraftStateAsset.Level1;

/**
 * Instances of this unit test validate the {@link WarcraftStateAdapter} class.
 *
 * @author Blair Butterworth
 */
public class WarcraftStateAdapterTest extends GameTestCase
{
    private WarcraftStateAdapter adapter;
    private WarcraftStateAssetLoader assetLoader;
    private TiledMapLoader mapLoader;
    private TestFileHandleResolver resolver;

    @Before
    public void setup() {
        super.setup();
        resolver = new TestFileHandleResolver();
        mapLoader = new TiledMapLoader(resolver);
        assetLoader = new WarcraftStateAssetLoader(itemFactory, mapLoader);
        adapter = new WarcraftStateAdapter(itemFactory, behaviourFactory, assetLoader);
    }

    @Test
    public void deserializeSaveTest() {
        FileHandle handle = resolver.resolve("/warcraft/state/save.json");

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
        resolver.respondWith(Level1.getFilePath(), "/warcraft/state/level.tmx");
        FileHandle handle = resolver.resolve("/warcraft/state/level.json");

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