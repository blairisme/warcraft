/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.outro;

import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.test.testcase.StringBundleTestCase;

/**
 * Instances of this unit test validate logic in the {@link OutroMenuStrings}
 * class.
 *
 * @author Blair Butterworth
 */
public class OutroMenuStringsTest extends StringBundleTestCase<OutroMenuStrings>
{
    @Override
    protected OutroMenuStrings getBundleWrapper(I18NBundle bundle) {
        return new OutroMenuStrings(bundle);
    }

    @Override
    protected String getBundleAsset() {
        return "data/strings/common/menu/outro";
    }
}