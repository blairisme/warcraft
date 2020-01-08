/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.gatherer.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.combatant.gatherer.GathererAssets;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Death;
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
        super(7);

        requireNonNull(base);
        requireNonNull(oil);
        requireNonNull(decompose);

        idle(base, oil, size);
        move(base, oil, size);
        death(base, decompose, size);
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

    private void death(Texture base, Texture decompose, GridPoint2 size) {
        sequence(Death)
            .element()
                .withTexture(base)
                .withSequence(size.y, 2)
                .withSize(size)
                .withInterval(0.5f)
            .element()
                .withTexture(decompose)
                .withSequence(432, 1)
                .withSize(size)
                .withInterval(2f);

    }
}
