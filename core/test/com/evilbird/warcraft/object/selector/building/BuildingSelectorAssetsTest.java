/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.selector.building;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.state.WarcraftContext;
import org.junit.Assert;
import org.junit.Test;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftSeason.Winter;
import static com.evilbird.warcraft.object.unit.UnitType.Barracks;
import static com.evilbird.warcraft.object.unit.UnitType.Farm;
import static com.evilbird.warcraft.object.unit.UnitType.TownHall;

/**
 * Instances of this unit test validate logic in the {@link BuildingSelectorAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class BuildingSelectorAssetsTest extends AssetBundleTestCase<BuildingSelectorAssets>
{
    @Override
    protected BuildingSelectorAssets getAssetBundle(AssetManager assets) {
        return new BuildingSelectorAssets(assets, new WarcraftContext(Human, Winter));
    }

    @Test
    public void getTest() {
        bundle.load();
        assets.finishLoading();
        Assert.assertNotNull(bundle.getAllowed(Barracks));
        Assert.assertNotNull(bundle.getProhibited(TownHall));
        Assert.assertNotNull(bundle.getBuilding(Farm));
    }

    @Test (expected = GdxRuntimeException.class)
    public void getWithoutLoadingTest()  {
        Assert.assertNotNull(bundle.getAllowed(Barracks));
        Assert.assertNotNull(bundle.getProhibited(TownHall));
        Assert.assertNotNull(bundle.getBuilding(Farm));
    }
}