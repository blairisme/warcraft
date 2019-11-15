/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.ranged;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnitAssets;

/**
 * Instances of this unit test validate logic in the {@link FlyingUnitAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class RangedUnitAssetsTest extends AssetBundleTestCase<RangedUnitAssets>
{
    @Override
    protected RangedUnitAssets getAssetBundle(AssetManager assets) {
        return new RangedUnitAssets(assets, UnitType.ElvenArcher);
    }
}