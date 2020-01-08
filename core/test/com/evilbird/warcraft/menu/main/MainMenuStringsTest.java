/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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