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
import com.evilbird.engine.menu.Menu;
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.warcraft.menu.ingame.IngameMenuFactory;
import com.evilbird.warcraft.menu.ingame.IngameMenuType;
import com.evilbird.warcraft.menu.intro.IntroMenuFactory;
import com.evilbird.warcraft.menu.main.MainMenuFactory;
import com.evilbird.warcraft.menu.main.MainMenuType;

import javax.inject.Inject;

public class WarcraftMenuFactory implements MenuFactory
{
    private IdentifiedAssetProviderSet<Menu> factories;

    @Inject
    public WarcraftMenuFactory(
            MainMenuFactory mainMenuFactory,
            IntroMenuFactory introMenuFactory,
            IngameMenuFactory ingameMenuFactory)
    {
        this.factories = new IdentifiedAssetProviderSet<>();
        this.factories.addProvider(mainMenuFactory);
        this.factories.addProvider(introMenuFactory);
        factories.addProvider(IngameMenuType.values(), ingameMenuFactory);
    }

    @Override
    public void load() {
        factories.load();
    }

    @Override
    public Menu newMenu() {
        return newMenu(MainMenuType.Home);
    }

    @Override
    public Menu newMenu(MenuIdentifier identifier) {
        return factories.get(identifier);
    }
}
