/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.common.WarcraftContext;
import com.evilbird.warcraft.item.unit.UnitType;

import static com.evilbird.warcraft.common.WarcraftAssetSet.Winter;
import static com.evilbird.warcraft.common.WarcraftFaction.Human;

/**
 * Instances of this unit test validate logic in the {@link BuildingAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class BuildingAssetsTest extends AssetBundleTestCase<BuildingAssets>
{
    @Override
    protected BuildingAssets getAssetBundle(AssetManager assets) {
        return new BuildingAssets(assets, UnitType.Barracks, new WarcraftContext(Human, Winter));
    }
}