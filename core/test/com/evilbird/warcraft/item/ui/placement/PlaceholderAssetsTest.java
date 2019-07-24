/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.placement;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.common.WarcraftContext;
import org.junit.Assert;
import org.junit.Test;

import static com.evilbird.warcraft.common.WarcraftAssetSet.Winter;
import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.item.unit.UnitType.Barracks;
import static com.evilbird.warcraft.item.unit.UnitType.Farm;
import static com.evilbird.warcraft.item.unit.UnitType.TownHall;

/**
 * Instances of this unit test validate logic in the {@link PlaceholderAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class PlaceholderAssetsTest extends AssetBundleTestCase<PlaceholderAssets>
{
    @Override
    protected PlaceholderAssets getAssetBundle(AssetManager assets) {
        return new PlaceholderAssets(assets, new WarcraftContext(Human, Winter));
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