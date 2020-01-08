/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.ingame;

import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.test.testcase.StringBundleTestCase;

/**
 * Instances of this unit test validate logic in the {@link IngameMenuStrings}
 * class.
 *
 * @author Blair Butterworth
 */
public class IngameMenuStringsTest extends StringBundleTestCase<IngameMenuStrings>
{
    @Override
    protected IngameMenuStrings getBundleWrapper(I18NBundle bundle) {
        return new IngameMenuStrings(bundle, bundle);
    }

    @Override
    protected String getBundleAsset() {
        return "data/strings/common/menu/ingame";
    }
}