/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.forest;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.common.WarcraftContext;

import static com.evilbird.warcraft.common.WarcraftAssetSet.Winter;
import static com.evilbird.warcraft.common.WarcraftFaction.Human;

/**
 * Instances of this unit test validate the {@link ForestAssets} class.
 *
 * @author Blair Butterworth
 */
public class ForestAssetsTest extends AssetBundleTestCase<ForestAssets>
{
    @Override
    protected ForestAssets getAssetBundle(AssetManager assets) {
        return new ForestAssets(assets, new WarcraftContext(Human, Winter));
    }
}