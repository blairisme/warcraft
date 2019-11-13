/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.conjured;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;

/**
 * Instances of this unit test validate logic in the {@link ConjuredAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class ConjuredAssetsTest extends AssetBundleTestCase<ConjuredAssets>
{
    @Override
    protected ConjuredAssets getAssetBundle(AssetManager assets) {
        return new ConjuredAssets(assets);
    }
}