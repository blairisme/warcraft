/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.critter.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.critter.CritterAssets;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Attack;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;
import static java.util.Objects.requireNonNull;

/**
 * Defines a catalog of animations as laid out in critter texture atlas files.
 *
 * @author Blair Butterworth
 */
public class CritterAnimations extends AnimationCatalog
{
    private static final GridPoint2 SIZE = new GridPoint2(32, 32);

    public CritterAnimations(CritterAssets assets) {
        this(assets.getBaseTexture(), SIZE);
    }

    public CritterAnimations(Texture base, GridPoint2 size) {
        super(4);

        requireNonNull(base);
        requireNonNull(size);

        idle(base, size);
        death(base, size);
    }

    private void idle(Texture base, GridPoint2 size) {
        alias(Attack, Idle);
        alias(Move, Idle);
        animation(Idle)
            .withTexture(base)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(10f)
            .looping();
    }

    private void death(Texture base, GridPoint2 size) {
        animation(Death)
            .withTexture(base)
            .withSequence(size.y, 1)
            .withSize(size)
            .withInterval(0.5f)
            .notLooping();
    }
}
