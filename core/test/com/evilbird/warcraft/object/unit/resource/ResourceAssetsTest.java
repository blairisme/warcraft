/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.object.unit.gatherer.GathererAssets;
import com.evilbird.warcraft.state.WarcraftContext;

import static com.evilbird.warcraft.common.WarcraftFaction.Orc;
import static com.evilbird.warcraft.common.WarcraftSeason.Swamp;
import static com.evilbird.warcraft.object.unit.UnitType.GoldMine;

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
        return new ResourceAssets(assets, GoldMine, new WarcraftContext(Orc, Swamp));
    }
}