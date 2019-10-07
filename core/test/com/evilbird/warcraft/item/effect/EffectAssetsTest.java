/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.effect;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;

/**
 * Instances of this unit test validate logic in the {@link EffectAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class EffectAssetsTest extends AssetBundleTestCase<EffectAssets>
{
    @Override
    protected EffectAssets getAssetBundle(AssetManager assets) {
        return new EffectAssets(assets);
    }
}