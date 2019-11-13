/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.layer.fog;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.state.WarcraftContext;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftSeason.Summer;

/**
 * Instances of this unit test validate the {@link FogAssets} class.
 *
 * @author Blair Butterworth
 */
public class FogAssetsTest extends AssetBundleTestCase<FogAssets>
{
    @Override
    protected FogAssets getAssetBundle(AssetManager assets) {
        return new FogAssets(assets, new WarcraftContext(Human, Summer));
    }
}