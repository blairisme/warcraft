/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.selector.target;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;

/**
 * Instances of this unit test validate logic in the {@link TargetSelectorAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class TargetSelectorAssetsTest extends AssetBundleTestCase<TargetSelectorAssets>
{
    @Override
    protected TargetSelectorAssets getAssetBundle(AssetManager assets) {
        return new TargetSelectorAssets(assets);
    }
}