/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.intro;

import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.test.testcase.StringBundleTestCase;

/**
 * Instances of this unit test validate logic in the {@link IntroMenuStrings}
 * class.
 *
 * @author Blair Butterworth
 */
public class IntroMenuStringsTest extends StringBundleTestCase<IntroMenuStrings>
{
    @Override
    protected IntroMenuStrings getBundleWrapper(I18NBundle bundle) {
        String objectivesPath = "data/strings/human/menu/objectives";
        assets.load(objectivesPath, I18NBundle.class);
        assets.finishLoadingAsset(objectivesPath);
        I18NBundle objectivesBundle = assets.get(objectivesPath, I18NBundle.class);
        return new IntroMenuStrings(bundle, objectivesBundle, IntroMenuType.Human1);
    }

    @Override
    protected String getBundleAsset() {
        return "data/strings/human/menu/intro1";
    }
}