/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.melee;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.test.testcase.AnimationCatalogTestCase;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;

import java.util.Arrays;
import java.util.Collection;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Attack;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;

/**
 * Instances of this unit test validate logic in the {@link DemoUnitAnimations}
 * class.
 *
 * @author Blair Butterworth
 */
public class DemoUnitAnimationsTest extends AnimationCatalogTestCase<DemoUnitAnimations, CombatantAssets>
{
    @Override
    protected CombatantAssets newAssets(AssetManager manager) {
        return new CombatantAssets(manager, UnitType.DwarvenDemolitionSquad);
    }

    @Override
    protected DemoUnitAnimations newCatalog(CombatantAssets assets) {
        return new DemoUnitAnimations(assets);
    }

    @Override
    protected Collection<Identifier> getAnimationsIds() {
        return Arrays.asList(Attack, Idle, Move);
    }
}