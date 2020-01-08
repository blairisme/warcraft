/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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