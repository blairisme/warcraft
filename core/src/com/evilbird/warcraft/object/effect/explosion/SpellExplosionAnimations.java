/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.effect.explosion;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;

/**
 * Defines explosion animations as laid out in explosion texture atlas files.
 *
 * @author Blair Butterworth
 */
public class SpellExplosionAnimations extends AnimationCatalog
{
    private static final GridPoint2 SIZE = new GridPoint2(32, 32);

    public SpellExplosionAnimations(Texture texture) {
        super(1);

        animation(Idle)
            .withTexture(texture)
            .withSequence(1, 5)
            .withSize(SIZE)
            .withInterval(0.10f)
            .singleDirection()
            .notLooping();
    }
}
