/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.layer.wall;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.state.WarcraftContext;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftSeason.Winter;

/**
 * Instances of this unit test validate the {@link WallAssets} class.
 *
 * @author Blair Butterworth
 */
public class WallAssetsTest extends AssetBundleTestCase<WallAssets>
{
    @Override
    protected WallAssets getAssetBundle(AssetManager assets) {
        return new WallAssets(assets, new WarcraftContext(Human, Winter));
    }
}