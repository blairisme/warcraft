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