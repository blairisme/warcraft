/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.object.unit.combatant.gatherer.GathererAssets;
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