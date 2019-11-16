/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.flying.orc;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnitAssets;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;
import static java.util.Objects.requireNonNull;

/**
 * Defines a catalog of animations as laid out in Goblin Zeppelin texture atlas
 * files.
 *
 * @author Blair Butterworth
 */
public class GoblinZeppelinAnimations extends AnimationCatalog
{
    /**
     * Creates a new instance of this class given a {@link FlyingUnitAssets}
     * bundle, containing the {@link FlyingUnitAssets#getBaseTexture() base}
     * texture required by Goblin Zeppelin animations. Animations contained
     * in this catalog will use the size specified by the asset bundle.
     *
     * @param assets an asset bundle, which must contain a valid base texture.
     *
     * @throws NullPointerException if the given asset bundle is {@code null}.
     */
    public GoblinZeppelinAnimations(FlyingUnitAssets assets) {
        this(assets.getBaseTexture(), assets.getExplosionTexture(), assets.getSize());
    }

    /**
     * Creates a new instance of this class given the texture used to display
     * the Goblin Zeppelin and the texture used when it explodes..
     *
     * @param base      a texture used for idle, movement and death animations.
     * @param explosion a texture used when the Goblin Zeppelin is killed.
     * @param size      the dimensions of the Goblin Zeppelin.
     *
     * @throws NullPointerException if the given textures or size {@code null}.
     */
    public GoblinZeppelinAnimations(Texture base, Texture explosion, GridPoint2 size) {
        super(3);

        requireNonNull(base);
        requireNonNull(size);

        death(explosion);
        idleAndMove(base, size);
    }

    private void death(Texture explosion) {
        animation(Death)
            .withTexture(explosion)
            .withSequence(0, 16)
            .withSize(64, 64)
            .withInterval(0.10f)
            .notLooping();
    }

    private void idleAndMove(Texture base, GridPoint2 size) {
        alias(Move, Idle);
        animation(Idle)
            .withTexture(base)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(10f)
            .looping();
    }
}
