/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.critter;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.object.unit.UnitType;

/**
 * Instances of this unit test validate logic in the {@link CritterAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class CritterAssetsTest extends AssetBundleTestCase<CritterAssets>
{
    @Override
    protected CritterAssets getAssetBundle(AssetManager assets) {
        return new CritterAssets(assets, UnitType.Seal);
    }
}
