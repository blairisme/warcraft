/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.effect;

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