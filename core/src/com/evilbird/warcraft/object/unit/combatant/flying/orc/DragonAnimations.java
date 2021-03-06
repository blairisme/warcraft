/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.flying.orc;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnitAssets;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Attack;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;
import static java.util.Objects.requireNonNull;

/**
 * Defines a catalog of animations as laid out in dragon texture atlas files.
 *
 * @author Blair Butterworth
 */
public class DragonAnimations extends AnimationCatalog
{
    /**
     * Creates a new instance of this class given a {@link FlyingUnitAssets}
     * bundle, containing the {@link FlyingUnitAssets#getBaseTexture() base}
     * texture required by dragon animations. Animations contained
     * in this catalog will use the size specified by the asset bundle.
     *
     * @param assets an asset bundle, which must contain a valid base texture.
     *
     * @throws NullPointerException if the given asset bundle is {@code null}.
     */
    public DragonAnimations(FlyingUnitAssets assets) {
        this(assets.getBaseTexture(), assets.getSize());
    }

    /**
     * Creates a new instance of this class given the texture that will used to
     * display dragon animations.
     *
     * @param base  a texture used for idle, movement and death animations.
     * @param size  the dimensions of the flying unit.
     *
     * @throws NullPointerException if the given asset bundle is {@code null}.
     */
    public DragonAnimations(Texture base, GridPoint2 size) {
        super(4);

        requireNonNull(base);
        requireNonNull(size);

        attack(base, size);
        death(base, size);
        idleAndMove(base, size);
    }

    private void attack(Texture base, GridPoint2 size) {
        animation(Attack)
            .withTexture(base)
            .withSequence(size.y * 3, 2)
            .withSize(size)
            .withInterval(0.5f)
            .reversed()
            .looping();
    }

    private void death(Texture base, GridPoint2 size) {
        animation(Death)
            .withTexture(base)
            .withSequence(size.y * 5, 5)
            .withBlankFrame()
            .withSize(size)
            .withInterval(0.15f)
            .notLooping();
    }

    private void idleAndMove(Texture base, GridPoint2 size) {
        alias(Move, Idle);
        animation(Idle)
            .withTexture(base)
            .withSequence(0, 4)
            .withSize(size)
            .withInterval(0.14f)
            .looping();
    }
}
