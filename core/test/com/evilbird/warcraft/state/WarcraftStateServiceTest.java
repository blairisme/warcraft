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
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceStorage;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.state.State;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.utils.TestFileHandleResolver;
import com.evilbird.warcraft.item.ui.hud.HudLoader;
import com.evilbird.warcraft.state.campaign.Campaign;
import com.evilbird.warcraft.state.map.Level;
import com.evilbird.warcraft.state.map.LevelLoader;
import com.evilbird.warcraft.state.scenario.ScenarioAdapter;
import com.evilbird.warcraft.state.scenario.ScenarioState;
import com.evilbird.warcraft.type.WarcraftTypeRegistry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static com.evilbird.test.data.device.TestDevices.newTestDevice;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Instances of this unit test validate the {@link WarcraftStateService} class.
 *
 * @author Blair Butterworth
 */
public class WarcraftStateServiceTest extends GameTestCase
{
    private JsonSerializer serializer;
    private HudLoader hudLoader;
    private GameController controller;
    private DeviceStorage deviceStorage;
    private TestFileHandleResolver assetStorage;
    private LevelLoader levelLoader;
    private ScenarioAdapter adapter;
    private WarcraftStateService service;

    @Before
    public void setup() {
        super.setup();
        device = newTestDevice();
        controller = Mockito.mock(GameController.class);
        deviceStorage = Mockito.mock(DeviceStorage.class);
        assetStorage = new TestFileHandleResolver();
        levelLoader = new LevelLoader(itemFactory, assetStorage);
        hudLoader = new HudLoader(device, itemFactory);
        adapter = new ScenarioAdapter(controller, hudLoader, levelLoader, behaviourFactory);
        serializer = new JsonSerializer(new WarcraftTypeRegistry(), Maps.of(ScenarioState.class, adapter));
        service = new WarcraftStateService(deviceStorage, assetStorage, serializer);
    }

    @Test
    public void listAssetsTest() throws Exception  {
        List<Identifier> result = service.list(WarcraftStateType.AssetState);
        Assert.assertNotNull(result);
        Assert.assertEquals(Campaign.values().length, result.size());
    }

    @Test
    public void listSavesTest() throws Exception {
        List<String> saves = Arrays.asList("save1", "save2", "save3");
        when(deviceStorage.list(anyString())).thenReturn(saves);

        List<Identifier> result = service.list(WarcraftStateType.UserState);
        Assert.assertNotNull(result);
        Assert.assertEquals(saves.size(), result.size());
    }

    @Test
    public void getAssetTest() throws Exception {
        assetStorage.respondWith(Campaign.Human1.getFilePath(), "/warcraft/state/level.json");
        assetStorage.respondWith(Level.Human1.getFilePath(), "/warcraft/state/level.tmx");

        State state = service.get(Campaign.Human1);
        Assert.assertNotNull(state);
    }

    @Test
    public void getSaveTest() throws Exception {
        TestFileHandleResolver resolver = new TestFileHandleResolver();
        FileHandle handle = resolver.resolve("/warcraft/state/save.json");

        WarcraftSave identifier = new WarcraftSave("getSaveTest");
        when(deviceStorage.read(anyString())).thenReturn(handle.reader());

        State state = service.get(identifier);
        Assert.assertNotNull(state);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getUnknownTest() throws Exception {
        StateIdentifier identifier = new StateIdentifier() {};
        service.get(identifier);
    }

//    @Test
//    public void setTest() throws Exception {
//        assetStorage.respondWith(Human1.getFilePath(), "/warcraft/state/level.json");
//        assetStorage.respondWith(Level1.getFilePath(), "/warcraft/state/level.tmx");
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