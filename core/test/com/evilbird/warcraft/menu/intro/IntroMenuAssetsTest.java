/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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