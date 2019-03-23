/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.intro;

import com.evilbird.engine.control.SelectListener;
import com.evilbird.warcraft.state.WarcraftStateScenario;

public class HumanIntroOne extends IntroMenu
{
    private static final String TITLE = "I. HILLSBRAD";
    private static final String OBJECTIVES = "Objectives:\n" +
            "-Build four Farms\n" +
            "-Build a Barracks\n";
    private static final String DESCRIPTION = "Due to your position as regional commander of the southern " +
            "defense forces, Lord Terenas commands that you raise an outpost" +
            " in the Hillsbrad foothills.\n" +
            "\n" +
            "It is rumored that Orcish marauders have been raiding coastal" +
            " towns in the area, but whether these attacks are part of a " +
            "greater Horde offensive is, as yet, unknown.\n" +
            "\n" +
            "Your outpost is to provide food and information for Alliance " +
            "troops and, as such, should be a community consisting of at " +
            "least four Farms.\n" +
            "\n" +
            "You must also construct a Barracks in order to safeguard the " +
            "Hillsbrad operation.";

    public HumanIntroOne() {
        setTitle(TITLE);
        setDescription(DESCRIPTION);
        setObjectives(OBJECTIVES);
        setButtonAction(showLevel());
    }

    private SelectListener showLevel() {
        return () -> {
            try {
                showState(WarcraftStateScenario.Human1);
            }
            catch (Exception exception) {
                exception.printStackTrace();
                //TODO - log error
                //TODO - show error menu
            }
        };
    }
}
