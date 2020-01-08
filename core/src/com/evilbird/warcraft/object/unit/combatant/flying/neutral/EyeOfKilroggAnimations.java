/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.flying.neutral;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnitAssets;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;

/**
 * Defines Eye of Kilrogg animations as laid out in Eye of Kilrogg texture
 * atlas files.
 *
 * @author Blair Butterworth
 */
public class EyeOfKilroggAnimations extends AnimationCatalog
{
    private static final GridPoint2 SIZE = new GridPoint2(32, 32);

    public EyeOfKilroggAnimations(FlyingUnitAssets assets) {
        this(assets.getBaseTexture());
    }

    public EyeOfKilroggAnimations(Texture texture) {
        super(2);

        alias(Move, Idle);
        animation(Idle)
            .withTexture(texture)
            .withSequence(0, 1)
            .withSize(SIZE)
            .withInterval(10f)
            .notLooping();
    }
}
