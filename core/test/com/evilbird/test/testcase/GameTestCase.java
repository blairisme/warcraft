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
import com.evilbird.engine.game.GameInjector;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.item.ItemFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mockito;

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
        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;
    }

    @AfterClass
    public static void cleanUp() {
        application.exit();
        application = null;
    }

    protected ItemFactory itemFactory;
    protected GameInjector gameInjector;
    protected GameService gameService;

    @Before
    public void setup() {
//        AssetManager assets = AssetManagerMocks.newAssetManagerMock();
//
//        FootmanFactory footmanFactory = new FootmanFactory(assets);
//        Item footman = footmanFactory.get();

        itemFactory = Mockito.mock(ItemFactory.class);
//        Mockito.when(itemFactory.newItem(Mockito.any())).thenReturn(footman);

        gameInjector = Mockito.mock(GameInjector.class);
        Mockito.when(gameInjector.getItemFactory()).thenReturn(itemFactory);

        gameService = GameService.getInstance();
        gameService.setInjector(gameInjector);
    }
}