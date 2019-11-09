/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.conjured;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.AnimationCatalog;

import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;

/**
 * Defines rune trap animations as laid out in rune trap texture atlas
 * files.
 *
 * @author Blair Butterworth
 */
public class ConjuredAnimations extends AnimationCatalog
{
    public ConjuredAnimations(Texture texture, GridPoint2 size) {
        super(1);

        animation(Idle)
            .withTexture(texture)
            .withSequence(0, 4)
            .withSize(size)
            .withInterval(0.15f)
            .singleDirection()
            .looping();
    }
}
