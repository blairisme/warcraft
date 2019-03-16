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
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.behaviour.BehaviourIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameEngine;
import com.evilbird.engine.game.GameInjector;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemType;
import com.evilbird.test.data.action.TestActions;
import com.evilbird.test.data.behaviour.TestBehaviours;
import com.evilbird.test.data.item.TestItemGroups;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.warcraft.item.data.DataType;
import com.evilbird.warcraft.item.layer.LayerType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.nio.IntBuffer;
import java.util.function.Supplier;

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

    protected GameInjector gameInjector;
    protected GameService gameService;
    protected GameEngine gameEngine;
    protected ItemFactory itemFactory;
    protected ActionFactory actionFactory;
    protected BehaviourFactory behaviourFactory;

    @Before
    public void setup() {
        gameEngine = Mockito.mock(GameEngine.class);
        actionFactory = Mockito.mock(ActionFactory.class);

        behaviourFactory = Mockito.mock(BehaviourFactory.class);
        respondWithNewBehaviour();

        itemFactory = Mockito.mock(ItemFactory.class);
        respondWithNewItem();

        gameInjector = Mockito.mock(GameInjector.class);
        Mockito.when(gameInjector.getGameEngine()).thenReturn(gameEngine);
        Mockito.when(gameInjector.getItemFactory()).thenReturn(itemFactory);
        Mockito.when(gameInjector.getActionFactory()).thenReturn(actionFactory);
        Mockito.when(gameInjector.getBehaviourFactory()).thenReturn(behaviourFactory);

        gameService = GameService.getInstance();
        gameService.setInjector(gameInjector);
    }

    public void respondWithNewItem() {
        //Mockito.when(itemFactory.newItem(Mockito.any())).thenAnswer((invocation) -> TestItems.newItem("item"));
            //TestItems.newItem(new TextIdentifier("item"), invocation.getArgument(0)));
        //respondWithItem(DataType.Player, () -> newItemGroup("Player1"));

        Mockito.when(itemFactory.newItem(Mockito.any())).thenAnswer(invocation -> {
            Identifier identifier = invocation.getArgument(0);
            if (identifier instanceof LayerType || identifier == DataType.Player) {
                return TestItemGroups.newItemGroup("Player1");
            }
            return TestItems.newItem("item");
        });
    }

    public void respondWithItem(Item item) {
        Mockito.when(itemFactory.newItem(Mockito.any())).thenReturn(item);
    }

    public void respondWithItem(ItemType identifier, Supplier<Item> supplier) {
        Mockito.when(itemFactory.newItem(identifier)).thenAnswer(invocation -> supplier.get());
    }

    public void respondWithNewAction() {
        Mockito.when(actionFactory.newAction(Mockito.any())).thenAnswer((invocation) -> TestActions.newAction("action"));
    }

    public void respondWithAction(Action action) {
        Mockito.when(actionFactory.newAction(Mockito.any())).thenReturn(action);
    }

    public void respondWithNewBehaviour() {
        Mockito.when(behaviourFactory.newBehaviour(Mockito.any())).thenAnswer((invocation) -> TestBehaviours.newBehaviour("behaviour"));
    }

    public void respondWithBehaviour(Behaviour behaviour) {
        Mockito.when(behaviourFactory.newBehaviour(Mockito.any())).thenReturn(behaviour);
    }

    public void respondWithBehaviour(Behaviour behaviour, BehaviourIdentifier identifier) {
        Mockito.when(behaviourFactory.newBehaviour(identifier)).thenReturn(behaviour);
    }
}