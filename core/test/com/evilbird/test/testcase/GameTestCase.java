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
import com.evilbird.engine.game.GameEngine;
import com.evilbird.engine.game.GameInjector;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.warcraft.action.attack.AttackActions;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.nio.IntBuffer;

import static org.mockito.Answers.RETURNS_MOCKS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

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
        Mockito.doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) {
                IntBuffer buffer = (IntBuffer)invocation.getArguments()[2];
                buffer.put(0, 1);
                return null;
            }
        }).when(Gdx.gl20).glGetShaderiv(anyInt(), anyInt(), any());
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                IntBuffer buffer = (IntBuffer)invocation.getArguments()[2];
                buffer.put(0, 1);
                return null;
            }
        }).when(Gdx.gl20).glGetProgramiv(anyInt(), anyInt(), any());
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

    @Before
    public void setup() {
        gameEngine = Mockito.mock(GameEngine.class);
        actionFactory = Mockito.mock(ActionFactory.class);

        itemFactory = Mockito.mock(ItemFactory.class);
        Mockito.when(itemFactory.newItem(Mockito.any())).thenAnswer((invocation) -> TestItems.newItem("item"));

        gameInjector = Mockito.mock(GameInjector.class);
        Mockito.when(gameInjector.getGameEngine()).thenReturn(gameEngine);
        Mockito.when(gameInjector.getItemFactory()).thenReturn(itemFactory);
        Mockito.when(gameInjector.getActionFactory()).thenReturn(actionFactory);

        gameService = GameService.getInstance();
        gameService.setInjector(gameInjector);
    }

    protected void respondWithItem(Item item) {
        Mockito.when(itemFactory.newItem(Mockito.any())).thenReturn(item);
    }

    protected void respondWithAction(Action action) {
        Mockito.when(actionFactory.newAction(Mockito.any())).thenReturn(action);
    }
}