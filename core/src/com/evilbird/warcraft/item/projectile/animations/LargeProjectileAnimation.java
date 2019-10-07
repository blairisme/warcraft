/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.projectile.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.AnimationCatalog;

import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;

/**
 * Defines large projectiles as laid out in large projectiles texture atlas
 * files.
 *
 * @author Blair Butterworth
 */
public class LargeProjectileAnimation extends AnimationCatalog
{
    private static final GridPoint2 SIZE = new GridPoint2(64, 64);

    public LargeProjectileAnimation(Texture texture) {
        super(1);

        animation(Idle)
            .withTexture(texture)
            .withSequence(0, 1)
            .withSize(SIZE)
            .withInterval(10f)
            .notLooping();
    }
}
