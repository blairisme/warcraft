/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.audio.sound.RandomSound;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.item.unit.UnitType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate logic in the {@link CombatantAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class CombatantAssetsTest extends AssetBundleTestCase<CombatantAssets>
{
    @Override
    protected CombatantAssets getAssetBundle(AssetManager assets) {
        return new CombatantAssets(assets, UnitType.Footman);
    }

    @Test
    public void soundSetTest() {
        bundle.load();
        assets.finishLoading();

        RandomSound effect = (RandomSound)bundle.getSelectedSound();
        Assert.assertEquals(3, effect.getSounds().size());
    }
}