/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.desktop;

import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.warcraft.menu.intro.IntroMenuType;
import com.evilbird.warcraft.menu.main.MainMenuType;
import com.evilbird.warcraft.menu.outro.OutroMenuType;
import com.evilbird.warcraft.state.WarcraftScenario;
import org.apache.commons.lang3.EnumUtils;
import picocli.CommandLine.Option;

/**
 * Contains commands provided to the game application when started.
 *
 * @author Blair Butterworth
 */
public class DesktopCommands
{
    @Option(names={"-s", "--scenario" }, paramLabel="LEVEL", description="starts the game and shows the given scenario")
    private WarcraftScenario scenario;

    @Option(names={"-m", "--menu" }, paramLabel="MENU", description="starts the game and shows the given menu")
    private String menu;

    /**
     * The scenario to show when the game engine starts. This command line
     * option is optional and may be <code>null</code>.
     *
     * @return a {@link WarcraftScenario}.
     */
    public WarcraftScenario getScenario() {
        return scenario;
    }

    /**
     * The menu to show when the game engine starts. This command line
     * option is optional and may be <code>null</code>.
     *
     * @return a {@link MenuIdentifier}.
     */
    public MenuIdentifier getMenu() {
        if (menu == null) {
            return null;
        }
        if (EnumUtils.isValidEnum(MainMenuType.class, menu)) {
            return MainMenuType.valueOf(menu);
        }
        if (EnumUtils.isValidEnum(IntroMenuType.class, menu)) {
            return IntroMenuType.valueOf(menu);
        }
        if (EnumUtils.isValidEnum(OutroMenuType.class, menu)) {
            return OutroMenuType.valueOf(menu);
        }
        throw new IllegalArgumentException("Unknown menu type: " + menu);
    }
}
