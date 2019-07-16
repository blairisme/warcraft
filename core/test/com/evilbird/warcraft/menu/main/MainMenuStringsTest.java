/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.main;

import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.test.testcase.StringBundleTestCase;

/**
 * Instances of this unit test validate logic in the {@link MainMenuStrings}
 * class.
 *
 * @author Blair Butterworth
 */
public class MainMenuStringsTest extends StringBundleTestCase<MainMenuStrings>
{
    @Override
    protected MainMenuStrings getBundleWrapper(I18NBundle bundle) {
        return new MainMenuStrings(bundle);
    }

    @Override
    protected String getBundleAsset() {
        return "data/strings/common/menu/main";
    }
}