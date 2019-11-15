/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.flying.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.test.testcase.AnimationCatalogTestCase;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnitAssets;

import java.util.Arrays;
import java.util.Collection;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;

/**
 * Instances of this unit test validate logic in the {@link GoblinZeppelinAnimations}
 * class.
 *
 * @author Blair Butterworth
 */
public class GoblinZeppelinAnimationsTest extends AnimationCatalogTestCase<GoblinZeppelinAnimations, FlyingUnitAssets>
{
    @Override
    protected FlyingUnitAssets newAssets(AssetManager manager) {
        return new FlyingUnitAssets(manager, UnitType.GoblinZeppelin);
    }

    @Override
    protected GoblinZeppelinAnimations newCatalog(FlyingUnitAssets assets) {
        return new GoblinZeppelinAnimations(assets);
    }

    @Override
    protected Collection<Identifier> getAnimationsIds() {
        return Arrays.asList(Idle, Move, Death);
    }
}