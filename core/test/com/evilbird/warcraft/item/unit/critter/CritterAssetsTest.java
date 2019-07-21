/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.critter;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.item.unit.UnitType;

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
