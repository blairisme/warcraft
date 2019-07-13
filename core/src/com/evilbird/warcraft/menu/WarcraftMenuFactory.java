/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu;

import com.evilbird.engine.game.GameFactoryComposite;
import com.evilbird.engine.menu.Menu;
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.warcraft.common.WarcraftContext;
import com.evilbird.warcraft.menu.ingame.IngameMenuFactory;
import com.evilbird.warcraft.menu.ingame.IngameMenuType;
import com.evilbird.warcraft.menu.intro.IntroMenuFactory;
import com.evilbird.warcraft.menu.intro.IntroMenuType;
import com.evilbird.warcraft.menu.main.MainMenuFactory;
import com.evilbird.warcraft.menu.main.MainMenuType;
import com.evilbird.warcraft.menu.outro.OutroMenuFactory;
import com.evilbird.warcraft.menu.outro.OutroMenuType;

import javax.inject.Inject;

import static com.evilbird.warcraft.common.WarcraftAssetSet.Winter;
import static com.evilbird.warcraft.common.WarcraftFaction.Human;

/**
 * Instances of this factory create {@link Menu Menus} whose contents are
 * specified by the given menu identifiers. This class supports creation of
 * main menus, level introduction and completion menus and in-game menus.
 *
 * @see MainMenuType
 * @see IntroMenuType
 * @see OutroMenuType
 * @see IngameMenuType
 *
 * @author Blair Butterworth
 */
public class WarcraftMenuFactory extends GameFactoryComposite<Menu> implements MenuFactory
{
    @Inject
    public WarcraftMenuFactory(
        MainMenuFactory mainMenuFactory,
        IntroMenuFactory introMenuFactory,
        OutroMenuFactory outroMenuFactory,
        IngameMenuFactory ingameMenuFactory)
    {
        addProvider(MainMenuType.values(), mainMenuFactory);
        addProvider(IntroMenuType.values(), introMenuFactory);
        addProvider(OutroMenuType.values(), outroMenuFactory);
        addProvider(IngameMenuType.values(), ingameMenuFactory);
    }

    @Override
    public Menu get() {
        return get(MainMenuType.Home);
    }

    @Override
    public void load() {
        load(new WarcraftContext(Human, Winter));
    }
}
