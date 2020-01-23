/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.fog;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;

/**
 * Instances of this unit test validate the {@link FogAssets} class.
 *
 * @author Blair Butterworth
 */
public class FogAssetsTest extends AssetBundleTestCase<FogAssets>
{
    @Override
    protected FogAssets getAssetBundle(AssetManager assets) {
        return new FogAssets(assets);
    }
}