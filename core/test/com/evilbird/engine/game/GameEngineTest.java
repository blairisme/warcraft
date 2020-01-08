/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.game;

import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.error.ErrorScreen;
import com.evilbird.engine.game.loader.LoaderScreen;
import com.evilbird.engine.menu.Menu;
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.engine.menu.MenuOverlay;
import com.evilbird.engine.menu.MenuScreen;
import com.evilbird.engine.preferences.GamePreferences;
import com.evilbird.engine.state.StateScreen;
import com.evilbird.engine.state.StateService;
import com.evilbird.test.testcase.GameTestCase;
import org.junit.Before;
import org.junit.Test;

import static com.evilbird.warcraft.menu.main.MainMenuType.Campaign;
import static com.evilbird.warcraft.menu.main.MainMenuType.Home;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Instances of this unit test validate the {@link GameEngine} class.
 *
 * @author Blair Butterworth
 */
public class GameEngineTest extends GameTestCase
{
    private GameEngine engine;
    private MenuScreen menuScreen;
    private MenuFactory menuFactory;

    @Before
    public void setup() {
        Device device = mock(Device.class);
        ErrorScreen errorScreen = mock(ErrorScreen.class);
        LoaderScreen loaderScreen = mock(LoaderScreen.class);
        menuScreen = mock(MenuScreen.class);
        MenuOverlay menuOverlay = mock(MenuOverlay.class);
        StateScreen stateScreen = mock(StateScreen.class);
        menuFactory = mock(MenuFactory.class);
        StateService stateService = mock(StateService.class);
        GameAssets gameAssets = mock(GameAssets.class);
        GamePreferences preferences = mock(com.evilbird.engine.preferences.GamePreferences.class);
        engine = new GameEngine(device,
            errorScreen, loaderScreen, menuScreen, menuOverlay,
            stateScreen, menuFactory, stateService, gameAssets, preferences);
    }

    @Test
    public void showRootMenuTest() {
        engine.showMenu();
        verify(menuScreen).show();
    }

    @Test
    public void showMenuTest() {
        Menu menu = mock(Menu.class);
        when(menuFactory.get(Campaign)).thenReturn(menu);

        engine.showMenu(Campaign);
        verify(menuScreen).setMenu(menu, Campaign);
        verify(menuScreen).show();
    }

    @Test
    public void showMenuAfterMenuTest() {
        Menu homeMenu = mock(Menu.class);
        Menu campaignMenu = mock(Menu.class);

        when(menuFactory.get(Home)).thenReturn(homeMenu);
        when(menuFactory.get(Campaign)).thenReturn(campaignMenu);

        engine.showMenu(Campaign);
        engine.showMenu(Home);

        verify(menuScreen, times(1)).setMenu(campaignMenu, Campaign);
        verify(menuScreen, times(1)).setMenu(homeMenu, Home);
        verify(menuScreen, times(2)).dispose();
        verify(menuScreen, times(2)).show();
    }
}