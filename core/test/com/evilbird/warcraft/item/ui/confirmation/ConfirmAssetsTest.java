/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.confirmation;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;

/**
 * Instances of this unit test validate logic in the {@link ConfirmAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class ConfirmAssetsTest extends AssetBundleTestCase<ConfirmAssets>
{
    @Override
    protected ConfirmAssets getAssetBundle(AssetManager assets) {
        return new ConfirmAssets(assets);
    }
}