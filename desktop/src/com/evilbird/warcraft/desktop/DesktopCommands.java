/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.desktop;

import com.evilbird.warcraft.state.WarcraftStateScenario;
import picocli.CommandLine.Option;

/**
 * Contains commands provided to the game application when started.
 *
 * @author Blair Butterworth
 */
public class DesktopCommands
{
    @Option(names={"-s", "--scenario" }, paramLabel="LEVEL", description="starts the game and shows the given scenario")
    private WarcraftStateScenario scenario;

    /**
     * The scenario to show when the game engine starts. This command line
     * option is optional and may be <code>null</code>.
     *
     * @return a {@link WarcraftStateScenario}.
     */
    public WarcraftStateScenario getScenario() {
        return scenario;
    }
}
