/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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