/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.level.Level;
import com.evilbird.engine.menu.IndexMenu;
import com.evilbird.engine.menu.IndexMenuFactory;
import com.evilbird.engine.menu.Menu;
import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.warcraft.menu.intro.IntroMenu;
import com.evilbird.warcraft.menu.intro.IntroMenuFactory;
import com.evilbird.warcraft.menu.main.MainMenu;
import com.evilbird.warcraft.menu.main.MainMenuFactory;

import javax.inject.Inject;
import javax.inject.Provider;

public class WarcraftMenuFactory implements IndexMenuFactory
{
    private Provider<Level> levelFactory;
    private IdentifiedAssetProviderSet<IndexMenu> factories;

    @Inject
    public WarcraftMenuFactory(
        Provider<Level> levelProvider,
        MainMenuFactory mainMenuFactory,
        IntroMenuFactory introMenuFactory)
    {
        levelFactory = levelProvider;
        factories = new IdentifiedAssetProviderSet<>();
        factories.addProvider(mainMenuFactory);
        factories.addProvider(introMenuFactory);
    }

    @Override
    public void load() {
        factories.load();
    }

    @Override
    public Menu newMenu() {
        return newMenu(MainMenu.Home);
        //return newMenu(IntroMenu.HumanLevel1);
    }

    @Override
    public Menu newMenu(MenuIdentifier identifier) {
        IndexMenu menu = factories.get(identifier);
        menu.setMenuFactory(this);
        menu.setLevelFactory(levelFactory);
        return menu;
    }
}
