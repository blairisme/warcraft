/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.flying.neutral;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.test.testcase.AnimationCatalogTestCase;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnitAssets;

import java.util.Arrays;
import java.util.Collection;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;

/**
 * Instances of this unit test validate logic in the {@link EyeOfKilroggAnimations}
 * class.
 *
 * @author Blair Butterworth
 */
public class EyeOfKilroggAnimationsTest extends AnimationCatalogTestCase<EyeOfKilroggAnimations, FlyingUnitAssets>
{
    @Override
    protected FlyingUnitAssets newAssets(AssetManager manager) {
        return new FlyingUnitAssets(manager, UnitType.EyeOfKilrogg);
    }

    @Override
    protected EyeOfKilroggAnimations newCatalog(FlyingUnitAssets assets) {
        return new EyeOfKilroggAnimations(assets);
    }

    @Override
    protected Collection<Identifier> getAnimationsIds() {
        return Arrays.asList(Idle, Move);
    }
}