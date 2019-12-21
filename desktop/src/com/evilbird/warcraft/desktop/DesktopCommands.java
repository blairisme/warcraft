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
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.warcraft.menu.intro.IntroMenuType;
import com.evilbird.warcraft.menu.main.MainMenuType;
import com.evilbird.warcraft.menu.outro.OutroMenuType;
import com.evilbird.warcraft.state.WarcraftCampaign;
import com.evilbird.warcraft.state.WarcraftScenario;
import org.apache.commons.lang3.EnumUtils;
import picocli.CommandLine.Option;

/**
 * Contains commands provided to the game application when started.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("unused")
public class DesktopCommands
{
    @Option(names={"-c", "--campaign" }, paramLabel="CAMPAIGN",
        description="starts the game and shows the given campaign")
    private WarcraftCampaign campaign;

    @Option(names={"-s", "--scenario" }, paramLabel="SCENARIO",
            description="starts the game and shows the given scenario")
    private WarcraftScenario scenario;

    @Option(names={"-m", "--menu" }, paramLabel="MENU",
        description="starts the game and shows the given menu")
    private String menu;

    @Option(names={"-d", "--debug-control" }, paramLabel="DEBUG CONTROL",
            description="game behaviour can be controlled via certain keys")
    private boolean debugControl;

    @Option(names={"-q", "--quick-build" }, paramLabel="QUICK BUILD",
        description="units are produced quickly")
    private boolean quickBuild;

    @Option(names={"-f", "--free-build" }, paramLabel="FREE BUILD",
        description="units are produced for free")
    private boolean freeBuild;

    @Option(names={"-u", "--all-upgrades" }, paramLabel="ALL UPGRADES",
            description="units are produced for free")
    private boolean upgradeCheat;

    /**
     * The state to show when the game engine starts. This command line
     * option is optional and may be {@code null}.
     *
     * @return a {@link StateIdentifier}.
     */
    public StateIdentifier getState() {
        if (campaign != null) {
            return campaign;
        }
        if (scenario != null) {
            return scenario;
        }
        return null;
    }

    /**
     * The menu to show when the game engine starts. This command line
     * option is optional and may be {@code null}.
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

    /**
     * Instructs the game engine to do certain things in response to various
     * key combinations.
     *
     * @return {@code true} if enabled.
     */
    public boolean isDebugControlEnabled() {
        return debugControl;
    }

    /**
     * Instructs the game engine to produce units and buildings quickly.
     *
     * @return {@code true} if enabled.
     */
    public boolean isQuickBuildEnabled() {
        return quickBuild;
    }

    /**
     * Instructs the game engine to produce units and buildings for free.
     *
     * @return {@code true} if enabled.
     */
    public boolean isFreeBuildEnabled() {
        return freeBuild;
    }

    /**
     * Instructs the game engine to make available all upgrades for free.
     *
     * @return {@code true} if enabled.
     */
    public boolean isUpgradeCheatEnabled() {
        return upgradeCheat;
    }
}
