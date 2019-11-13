/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.resource;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.AnimationCatalog;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.object.unit.UnitAnimation.ExtractGold;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static java.util.Objects.requireNonNull;

/**
 * Defines a catalog of animations as laid out in resource texture atlas files.
 *
 * @author Blair Butterworth
 */
public class ResourceAnimations extends AnimationCatalog
{
    private static final GridPoint2 SIZE = new GridPoint2(96, 96);
    private static final GridPoint2 DESTRUCTION = new GridPoint2(64, 64);

    /**
     * Creates a new instance of this class given a {@link ResourceAssets}
     * bundle, containing the {@link ResourceAssets#getGeneralTexture() base}
     * and {@link ResourceAssets#getDestructionTexture()} destruction} textures
     * required by resource animations. Animations contained in this catalog
     * will use the default melee unit size of 96 x 96.
     *
     * @param assets an asset bundle, which must contain a valid base and
     *               destruction texture.
     *
     * @throws NullPointerException if the given asset bundle is {@code null}.
     */
    public ResourceAnimations(ResourceAssets assets) {
        this(assets.getGeneralTexture(), assets.getDestructionTexture(), SIZE);
    }

    /**
     * Creates a new instance of this class given the base and decomposing
     * {@link Texture textures} required by resource animations.
     *
     * @param base          a texture used for idle, gathering and death
     *                      animations.
     * @param destruction   a texture used for destruction animation, shown
     *                      after the resource is destroyed.
     * @param size          the dimensions of the resource.
     *
     * @throws NullPointerException if either of the given textures or size
     *                              are {@code null}.
     */
    public ResourceAnimations(Texture base, Texture destruction, GridPoint2 size) {
        super(3);

        requireNonNull(base);
        requireNonNull(destruction);
        requireNonNull(size);

        idle(base, size);
        gathering(base, size);
        death(destruction);
    }

    private void idle(Texture base, GridPoint2 size) {
        animation(Idle)
            .withTexture(base)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(1f)
            .singleDirection()
            .looping();
    }

    private void gathering(Texture base, GridPoint2 size) {
        animation(ExtractGold)
            .withTexture(base)
            .withSequence(size.y, 1)
            .withSize(size)
            .withInterval(10f)
            .singleDirection()
            .looping();
    }

    private void death(Texture destruction) {
        animation(Death)
            .withTexture(destruction)
            .withSequence(0, 1)
            .withSize(DESTRUCTION)
            .withInterval(1f)
            .singleDirection()
            .looping();
    }
}