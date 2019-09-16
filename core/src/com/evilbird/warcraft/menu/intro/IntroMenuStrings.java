/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.intro;

import com.badlogic.gdx.utils.I18NBundle;

/**
 * A language specific bundle of strings used in introduction menus.
 *
 * @author Blair Butterworth
 */
public class IntroMenuStrings
{
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String OBJECTIVES = "objectives";

    private I18NBundle bundle;
    private I18NBundle objectives;
    private IntroMenuType type;

    public IntroMenuStrings(I18NBundle general, I18NBundle objectives, IntroMenuType type) {
        this.type = type;
        this.bundle = general;
        this.objectives = objectives;
    }

    public String getTitle() {
        return bundle.get(TITLE);
    }

    public String getDescription() {
        return bundle.get(DESCRIPTION);
    }

    public String getObjectives() {
        String points = objectives.get("intro" + type.getIndex());
        return bundle.format(OBJECTIVES, points);
    }
}
