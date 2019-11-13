/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.testcase;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.behaviour.BehaviourIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.reflect.TypeRegistry;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameEngine;
import com.evilbird.engine.game.GameInjector;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.GameObjectType;
import com.evilbird.test.data.action.TestActions;
import com.evilbird.test.data.behaviour.TestBehaviours;
import com.evilbird.test.data.item.TestBuildings;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.data.item.TestGatherers;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.data.item.TestPlayers;
import com.evilbird.test.data.item.TestResources;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.data.player.PlayerType;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.type.WarcraftTypeRegistry;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.nio.IntBuffer;
import java.util.function.Supplier;

import static com.evilbird.test.data.assets.TestDevices.newTestDevice;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.mockito.Answers.RETURNS_MOCKS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

/**
 * Instances of this test case set up the game engine ready for tests that
 * require elements of the LibGDX engine or main game services.
 *
 * @author Blair Butterworth
 */
public class GameTestCase
{
    private static Application application;

    @BeforeClass
    public static void init() {
        application = new HeadlessApplication(new ApplicationListener() {
            @Override public void create() {}
            @Override public void resize(int width, int height) {}
            @Override public void render() {}
            @Override public void pause() {}
            @Override public void resume() {}
            @Override public void dispose() {}
        });
        Gdx.gl20 = Mockito.mock(GL20.class, RETURNS_MOCKS);
        Gdx.gl = Gdx.gl20;
        Gdx.graphics = Mockito.mock(Graphics.class, RETURNS_MOCKS);

        Mockito.when(Gdx.graphics.getWidth()).thenReturn(1024);
        Mockito.when(Gdx.graphics.getHeight()).thenReturn(1024);

        Mockito.when(Gdx.gl20.glCreateShader(anyInt())).thenReturn(1);
        Mockito.when(Gdx.gl20.glCreateProgram()).thenReturn(1);
        Mockito.doAnswer(new IntBufferMockAnswer()).when(Gdx.gl20).glGetShaderiv(anyInt(), anyInt(), any());
        Mockito.doAnswer(new IntBufferMockAnswer()).when(Gdx.gl20).glGetProgramiv(anyInt(), anyInt(), any());

        ToStringBuilder.setDefaultStyle(SHORT_PREFIX_STYLE);
    }

    private static class IntBufferMockAnswer implements Answer<Object> {
        @Override
        public Object answer(InvocationOnMock invocation) {
            IntBuffer buffer = (IntBuffer)invocation.getArguments()[2];
            buffer.put(0, 1);
            return null;
        }
    }

    @AfterClass
    public static void cleanUp() {
        application.exit();
        application = null;
    }

    protected Device device;
    protected GameInjector gameInjector;
    protected GameService gameService;
    protected GameEngine gameEngine;
    protected GameObjectFactory objectFactory;
    protected ActionFactory actionFactory;
    protected BehaviourFactory behaviourFactory;
    protected TypeRegistry typeRegistry;
    protected WarcraftPreferences preferences;

    @Before
    public void setup() {
        device = newTestDevice();
        gameEngine = Mockito.mock(GameEngine.class);
        actionFactory = Mockito.mock(ActionFactory.class);

        behaviourFactory = Mockito.mock(BehaviourFactory.class);
        respondWithNewBehaviour();

        objectFactory = Mockito.mock(GameObjectFactory.class);
        respondWithNewItem();

        typeRegistry = new WarcraftTypeRegistry();
        preferences = Mockito.mock(WarcraftPreferences.class);

        gameInjector = Mockito.mock(GameInjector.class);
        Mockito.when(gameInjector.getDevice()).thenReturn(device);
        Mockito.when(gameInjector.getEngine()).thenReturn(gameEngine);
        Mockito.when(gameInjector.getItemFactory()).thenReturn(objectFactory);
        Mockito.when(gameInjector.getActionFactory()).thenReturn(actionFactory);
        Mockito.when(gameInjector.getBehaviourFactory()).thenReturn(behaviourFactory);
        Mockito.when(gameInjector.getTypeRegistry()).thenReturn(typeRegistry);
        Mockito.when(gameInjector.getPreferences()).thenReturn(preferences);

        gameService = GameService.getInstance();
        gameService.setInjector(gameInjector);
    }

    public void respondWithNewItem() {
        Mockito.when(objectFactory.get(Mockito.any())).thenAnswer(invocation -> {
            Identifier identifier = invocation.getArgument(0);
            if (identifier instanceof PlayerType) {
                return TestPlayers.newTestPlayer("player");
            }
            if (identifier == UnitType.Barracks) {
                return TestBuildings.newTestBuilding("barracks");
            }
            if (identifier == UnitType.Footman) {
                return TestCombatants.newTestCombatant("footman");
            }
            if (identifier == UnitType.Peasant) {
                return TestGatherers.newTestGatherer("peasant");
            }
            if (identifier == UnitType.GoldMine) {
                return TestResources.newTestResource("goldmine");
            }
            return TestItems.newItem("item");
        });
    }

    public void respondWithItem(GameObject gameObject) {
        Mockito.when(objectFactory.get(Mockito.any())).thenReturn(gameObject);
    }

    public void respondWithItem(GameObjectType identifier, Supplier<GameObject> supplier) {
        Mockito.when(objectFactory.get(identifier)).thenAnswer(invocation -> supplier.get());
    }

    public void respondWithNewAction() {
        Mockito.when(actionFactory.get(Mockito.any())).thenAnswer((invocation) -> TestActions.newAction("action"));
    }

    public void respondWithAction(Action action) {
        Mockito.when(actionFactory.get(Mockito.any())).thenReturn(action);
    }

    public void respondWithNewBehaviour() {
        Mockito.when(behaviourFactory.get(Mockito.any())).thenAnswer((invocation) -> TestBehaviours.newBehaviour("behaviour"));
    }

    public void respondWithBehaviour(Behaviour behaviour) {
        Mockito.when(behaviourFactory.get(Mockito.any())).thenReturn(behaviour);
    }

    public void respondWithBehaviour(Behaviour behaviour, BehaviourIdentifier identifier) {
        Mockito.when(behaviourFactory.get(identifier)).thenReturn(behaviour);
    }
}