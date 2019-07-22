/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.gatherer.GathererAssets;

/**
 * Instances of this unit test validate logic in the {@link GathererAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class ResourceAssetsTest extends AssetBundleTestCase<ResourceAssets>
{
    @Override
    protected ResourceAssets getAssetBundle(AssetManager assets) {
        return new ResourceAssets(assets, UnitType.GoldMine);
    }
}