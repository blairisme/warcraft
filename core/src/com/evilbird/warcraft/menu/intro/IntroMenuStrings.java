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

    public IntroMenuStrings(I18NBundle bundle) {
        this.bundle = bundle;
    }

    public String getTitle() {
        return bundle.get(TITLE);
    }

    public String getDescription() {
        return bundle.get(DESCRIPTION);
    }

    public String getObjectives() {
        return bundle.get(OBJECTIVES);
    }
}
