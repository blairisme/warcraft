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
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.serialization.JsonSerializer;
import com.evilbird.engine.state.State;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.test.utils.TestFileHandleResolver;
import com.evilbird.warcraft.item.ui.display.HudLoader;
import com.evilbird.warcraft.type.WarcraftTypeRegistry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.evilbird.warcraft.state.WarcraftCampaign.Human1;
import static org.mockito.Mockito.when;

/**
 * Instances of this unit test validate the {@link WarcraftStateService} class.
 *
 * @author Blair Butterworth
 */
public class WarcraftStateServiceTest extends StateTestCase
{
    private TestFileHandleResolver deviceResolver;
    private FileHandle assetDirectory;
    private FileHandle saveDirectory;
    private HudLoader hudLoader;
    private WarcraftMusic musicLoader;
    private JsonSerializer serializer;
    private WarcraftStateAdapter adapter;
    private WarcraftStateService service;

    @Before
    public void setup() {
        super.setup();
        assetDirectory = Mockito.mock(FileHandle.class);
        saveDirectory = Mockito.mock(FileHandle.class);
        deviceResolver = new TestFileHandleResolver();
        deviceResolver.respondWith("saves", saveDirectory);
        assetResolver.respondWith("data/levels", assetDirectory);
        hudLoader = new HudLoader(device, itemFactory);
        musicLoader = new WarcraftMusic(device);
        adapter = new WarcraftStateAdapter(hudLoader, levelLoader, musicLoader, behaviourFactory);
        serializer = new JsonSerializer(new WarcraftTypeRegistry(), Maps.of(WarcraftState.class, adapter));
        service = new WarcraftStateService(deviceResolver, assetResolver, serializer);
    }

    @Test
    public void listAssetsTest() {
        List<StateIdentifier> result = service.list(WarcraftStateType.AssetState);
        Assert.assertNotNull(result);
        Assert.assertEquals(WarcraftCampaign.values().length, result.size());
    }

    @Test
    public void listSavesTest() {
        FileHandle[] saves = new FileHandle[2];
        saves[0] = new FileHandle("save1.json");
        saves[1] = new FileHandle("save2.json");
        when(saveDirectory.list()).thenReturn(saves);

        List<StateIdentifier> result = service.list(WarcraftStateType.UserState);
        Assert.assertNotNull(result);
        Assert.assertEquals(saves.length, result.size());
    }

    @Test
    public void getAssetTest() {
        loadAssets();

        FileHandle scenario = assetResolver.resolve("/warcraft/state/level.json");
        FileHandle level = assetResolver.resolve("/warcraft/state/level.tmx");
        FileHandle faction = Mockito.mock(FileHandle.class);

        when(assetDirectory.child("human")).thenReturn(faction);
        when(faction.child("campaign1.json")).thenReturn(scenario);
        assetResolver.respondWith("data/levels/human/campaign1.tmx", level);

        State state = service.get(Human1);
        Assert.assertNotNull(state);
    }

    @Test
    public void getSaveTest()  {
        FileHandle scenario = assetResolver.resolve("/warcraft/state/save.json");
        when(saveDirectory.child("save.json")).thenReturn(scenario);

        WarcraftSave identifier = new WarcraftSave("save");
        State state = service.get(identifier);

        Assert.assertNotNull(state);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getUnknownTest()  {
        StateIdentifier identifier = Mockito.mock(StateIdentifier.class);
        service.get(identifier);
    }






//    @Test
//    public void setTest() throws Exception {
//        assetResolver.respondWith(Human1.getFilePath(), "/warcraft/state/level.json");
//        assetResolver.respondWith(Level1.getFilePath(), "/warcraft/state/level.tmx");
//
//        State state = service.get(Human1);
//        Assert.assertNotNull(state);
//
//        WarcraftSave identifier = new WarcraftSave("setTest");
//        service
//    }
//
//    @Test (expected = IllegalArgumentException.class)
//    public void setUnknownIdentifierTest() {
//
//    }
//
//    @Test (expected = IllegalArgumentException.class)
//    public void setUnknownStateTest() {
//
//    }
//
//    @Test
//    public void removeTest() {
//
//    }
//
//    @Test (expected = IllegalArgumentException.class)
//    public void removeUnknownIdentifierTest() {
//
//    }
}