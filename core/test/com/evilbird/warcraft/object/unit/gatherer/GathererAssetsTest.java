/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.gatherer;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.gatherer.GathererAssets;

/**
 * Instances of this unit test validate logic in the {@link com.evilbird.warcraft.object.unit.combatant.gatherer.GathererAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class GathererAssetsTest extends AssetBundleTestCase<com.evilbird.warcraft.object.unit.combatant.gatherer.GathererAssets>
{
    @Override
    protected com.evilbird.warcraft.object.unit.combatant.gatherer.GathererAssets getAssetBundle(AssetManager assets) {
        return new GathererAssets(assets, UnitType.Peasant);
    }
}