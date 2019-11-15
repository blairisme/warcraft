/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.flying;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.object.unit.UnitType;

/**
 * Instances of this unit test validate logic in the {@link FlyingUnitAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class FlyingUnitAssetsTest extends AssetBundleTestCase<FlyingUnitAssets>
{
    @Override
    protected FlyingUnitAssets getAssetBundle(AssetManager assets) {
        return new FlyingUnitAssets(assets, UnitType.GnomishFlyingMachine);
    }
}