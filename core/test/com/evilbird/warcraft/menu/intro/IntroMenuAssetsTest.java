/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.intro;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;

/**
 * Instances of this unit test validate logic in the {@link IntroMenuAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class IntroMenuAssetsTest extends AssetBundleTestCase<IntroMenuAssets>
{
    @Override
    protected IntroMenuAssets getAssetBundle(AssetManager assets) {
        return new IntroMenuAssets(assets, IntroMenuType.Human1);
    }
}