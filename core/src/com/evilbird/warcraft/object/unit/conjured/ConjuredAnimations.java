/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.conjured;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;

/**
 * Defines rune trap animations as laid out in rune trap texture atlas
 * files.
 *
 * @author Blair Butterworth
 */
public class ConjuredAnimations extends AnimationCatalog
{
    public ConjuredAnimations(ConjuredAssets assets, GridPoint2 size) {
        super(1);

        animation(Idle)
            .withTexture(assets.getBaseTexture())
            .withSequence(0, 4)
            .withSize(size)
            .withInterval(0.15f)
            .singleDirection()
            .looping();
    }
}
