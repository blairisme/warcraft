/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.effect.environmental;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;

/**
 * Defines fire animations as laid out in fire texture atlas files.
 *
 * @author Blair Butterworth
 */
public class FireAnimations extends AnimationCatalog
{
    private static final GridPoint2 SIZE = new GridPoint2(48, 48);

    public FireAnimations(Texture texture) {
        super(1);

        animation(Idle)
            .withTexture(texture)
            .withSequence(0, 10)
            .withSize(SIZE)
            .withInterval(0.15f)
            .singleDirection()
            .looping();
    }
}
