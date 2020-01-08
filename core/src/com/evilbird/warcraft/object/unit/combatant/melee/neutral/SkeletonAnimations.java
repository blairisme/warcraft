/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.melee.neutral;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Attack;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;
import static java.util.Objects.requireNonNull;

/**
 * Defines a catalog of animations as laid out in skeleton texture atlas
 * files. Animations are provided for unit attack, movement, idle, death and
 * decomposition.
 *
 * @author Blair Butterworth
 */
public class SkeletonAnimations extends AnimationCatalog
{
    protected static final GridPoint2 SIZE = new GridPoint2(72, 72);

    /**
     * Creates a new instance of this class given a {@link CombatantAssets}
     * bundle, containing the assets required by skeleton combatant animations.
     * Animations contained in this catalog will use the default size of 72 x 72.
     *
     * @param assets an asset bundle, which must contain a valid base and
     *               decompose texture.
     *
     * @throws NullPointerException if the given asset bundle is {@code null}.
     */
    public SkeletonAnimations(CombatantAssets assets) {
        this(assets.getBaseTexture());
    }

    /**
     * Creates a new instance of this class given the {@link Texture textures}
     * required by skeleton combatant animations. Animations contained in this
     * catalog will use the default unit size of 72 x 72.
     *
     * @param base      a texture used for idle, movement, attack and death
     *                  animations.
     *
     * @throws NullPointerException if either of the given textures are
     *                              {@code null}.
     */
    public SkeletonAnimations(Texture base) {
        this(base, SIZE);
    }

    /**
     * Creates a new instance of this class given the {@link Texture textures}
     * required by skeleton combatant animations.
     *
     * @param base      a texture used for idle, movement, attack and death
     *                  animations.
     * @param size      the dimensions of the melee unit.
     *
     * @throws NullPointerException if either of the given textures or size
     *                              are {@code null}.
     */
    public SkeletonAnimations(Texture base, GridPoint2 size) {
        super(4);

        requireNonNull(base);
        requireNonNull(size);

        attack(base, size);
        death(base,  size);
        idle(base, size);
        move(base, size);
    }

    private void attack(Texture base, GridPoint2 size) {
        animation(Attack)
            .withTexture(base)
            .withSequence(size.y * 5, 3)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(0.15f)
            .looping();
    }

    private void idle(Texture base, GridPoint2 size) {
        animation(Idle)
            .withTexture(base)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(10f)
            .looping();
    }

    private void move(Texture base, GridPoint2 size) {
        animation(Move)
            .withTexture(base)
            .withSequence(0, 5)
            .withSize(size)
            .withInterval(0.10f)
            .looping();
    }

    private void death(Texture base, GridPoint2 size) {
        animation(Death)
            .withTexture(base)
            .withSequence(size.y * 9, 6)
            .withSize(size)
            .withInterval(0.15f)
            .notLooping();
    }
}
