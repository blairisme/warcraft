/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.main;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.menu.outro.OutroMenuAssets;

/**
 * Instances of this unit test validate logic in the {@link OutroMenuAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class MainMenuAssetsTest extends AssetBundleTestCase<MainMenuAssets>
{
    @Override
    protected MainMenuAssets getAssetBundle(AssetManager assets) {
        return new MainMenuAssets(assets);
    }
}