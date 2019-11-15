/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.ranged;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.test.testcase.SoundCatalogTestCase;
import com.evilbird.warcraft.object.unit.UnitType;

import java.util.Arrays;
import java.util.Collection;

import static com.evilbird.warcraft.object.unit.UnitSound.Acknowledge;
import static com.evilbird.warcraft.object.unit.UnitSound.Attack;
import static com.evilbird.warcraft.object.unit.UnitSound.Captured;
import static com.evilbird.warcraft.object.unit.UnitSound.Die;
import static com.evilbird.warcraft.object.unit.UnitSound.Hit;
import static com.evilbird.warcraft.object.unit.UnitSound.Ready;
import static com.evilbird.warcraft.object.unit.UnitSound.Rescued;
import static com.evilbird.warcraft.object.unit.UnitSound.Selected;

/**
 * Instances of this unit test validate logic in the {@link RangedUnitSounds}
 * class.
 *
 * @author Blair Butterworth
 */
public class RangedUnitSoundsTest extends SoundCatalogTestCase<RangedUnitSounds, RangedUnitAssets>
{
    @Override
    protected RangedUnitAssets newAssets(AssetManager manager) {
        return new RangedUnitAssets(manager, UnitType.ElvenArcher);
    }

    @Override
    protected RangedUnitSounds newCatalog(RangedUnitAssets assets) {
        return new RangedUnitSounds(assets);
    }

    @Override
    protected Collection<Identifier> getSoundIds() {
        return Arrays.asList(Acknowledge, Selected, Attack, Die, Ready, Captured, Rescued, Hit);
    }
}