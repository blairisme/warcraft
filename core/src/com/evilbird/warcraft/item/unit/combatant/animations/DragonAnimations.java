/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.AnimationCatalog;
import com.evilbird.warcraft.item.unit.combatant.CombatantAssets;

import static com.evilbird.warcraft.item.unit.UnitAnimation.Attack;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Move;
import static java.util.Objects.requireNonNull;

/**
 * Defines a catalog of animations as laid out in dragon texture atlas files.
 *
 * @author Blair Butterworth
 */
public class DragonAnimations extends AnimationCatalog
{
    /**
     * Creates a new instance of this class given a {@link CombatantAssets}
     * bundle, containing the {@link CombatantAssets#getBaseTexture() base}
     * texture required by dragon animations. Animations contained
     * in this catalog will use the size specified by the asset bundle.
     *
     * @param assets an asset bundle, which must contain a valid base texture.
     *
     * @throws NullPointerException if the given asset bundle is {@code null}.
     */
    public DragonAnimations(CombatantAssets assets) {
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
            .withInterval(0.12f)
            .looping();
    }
}
