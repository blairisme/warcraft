/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.audio.sound.RandomSound;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.object.unit.UnitType;
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