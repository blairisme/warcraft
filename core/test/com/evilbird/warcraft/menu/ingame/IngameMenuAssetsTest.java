/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.state.WarcraftContext;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftSeason.Winter;

/**
 * Instances of this unit test validate logic in the {@link IngameMenuAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class IngameMenuAssetsTest extends AssetBundleTestCase<IngameMenuAssets>
{
    @Override
    protected IngameMenuAssets getAssetBundle(AssetManager assets) {
        return new IngameMenuAssets(assets, new WarcraftContext(Human, Winter));
    }
}