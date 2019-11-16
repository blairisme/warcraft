/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.gatherer.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.gatherer.GathererAssets;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Decompose;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.IdleBasic;
import static com.evilbird.warcraft.object.unit.UnitAnimation.IdleOil;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;
import static com.evilbird.warcraft.object.unit.UnitAnimation.MoveBasic;
import static com.evilbird.warcraft.object.unit.UnitAnimation.MoveOil;
import static java.util.Objects.requireNonNull;

/**
 * Defines a catalog of animations as laid out in sea gatherer texture atlas
 * files.
 *
 * @author Blair Butterworth
 */
public class SeaGathererAnimations extends AnimationCatalog
{
    private static final GridPoint2 SIZE = new GridPoint2(72, 72);

    public SeaGathererAnimations(GathererAssets assets) {
        this(assets.getBaseTexture(),
            assets.getMoveWithOilTexture(),
            assets.getDecomposeTexture(),
            SIZE);
    }

    public SeaGathererAnimations(Texture base, Texture oil, Texture decompose, GridPoint2 size) {
        super(8);

        requireNonNull(base);
        requireNonNull(oil);
        requireNonNull(decompose);

        idle(base, oil, size);
        move(base, oil, size);
        death(base, size);
        decompose(decompose, size);
    }

    private void idle(Texture base, Texture oil, GridPoint2 size) {
        alias(Idle, IdleBasic);

        animation(IdleBasic)
            .withTexture(base)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(10f)
            .looping();

        animation(IdleOil)
            .withTexture(oil)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(10f)
            .looping();
    }

    private void move(Texture base, Texture oil, GridPoint2 size) {
        alias(Move, MoveBasic);

        animation(MoveBasic)
            .withTexture(base)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(10f)
            .looping();

        animation(MoveOil)
            .withTexture(oil)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(10f)
            .looping();
    }

    private void death(Texture base, GridPoint2 size) {
        animation(Death)
            .withTexture(base)
            .withSequence(size.y, 2)
            .withSize(size)
            .withInterval(0.5f)
            .notLooping();
    }

    private void decompose(Texture decompose, GridPoint2 size) {
        animation(Decompose)
            .withTexture(decompose)
            .withSequence(432, 1)
            .withSize(size)
            .withInterval(2f)
            .notLooping();
    }
}
