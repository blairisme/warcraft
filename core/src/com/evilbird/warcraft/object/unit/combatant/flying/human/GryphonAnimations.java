/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.flying.human;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnitAssets;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Attack;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;
import static java.util.Objects.requireNonNull;

/**
 * Defines a catalog of animations as laid out in Gryphon texture atlas files.
 *
 * @author Blair Butterworth
 */
public class GryphonAnimations extends AnimationCatalog
{
    /**
     * Creates a new instance of this class given a {@link CombatantAssets}
     * bundle, containing the {@link CombatantAssets#getBaseTexture() base}
     * texture required by Gryphon animations. Animations contained
     * in this catalog will use the size specified by the asset bundle.
     *
     * @param assets an asset bundle, which must contain a valid base texture.
     *
     * @throws NullPointerException if the given asset bundle is {@code null}.
     */
    public GryphonAnimations(FlyingUnitAssets assets) {
        this(assets.getBaseTexture(), assets.getSize());
    }

    /**
     * Creates a new instance of this class given the texture that will used to
     * display Gryphon animations.
     *
     * @param base  a texture used for idle, movement and death animations.
     * @param size  the dimensions of the flying unit.
     *
     * @throws NullPointerException if the given asset bundle is {@code null}.
     */
    public GryphonAnimations(Texture base, GridPoint2 size) {
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
            .withSequence(size.y * 4, 3)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(0.15f)
            .notLooping();
    }

    private void death(Texture base, GridPoint2 size) {
        animation(Death)
            .withTexture(base)
            .withSequence(size.y * 7, 6)
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
            .withInterval(0.20f)
            .looping();
    }
}
