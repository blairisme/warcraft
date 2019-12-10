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

import static com.evilbird.warcraft.object.unit.UnitAnimation.Attack;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.object.unit.UnitAnimation.GatherWood;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.IdleBasic;
import static com.evilbird.warcraft.object.unit.UnitAnimation.IdleGold;
import static com.evilbird.warcraft.object.unit.UnitAnimation.IdleWood;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;
import static com.evilbird.warcraft.object.unit.UnitAnimation.MoveBasic;
import static com.evilbird.warcraft.object.unit.UnitAnimation.MoveGold;
import static com.evilbird.warcraft.object.unit.UnitAnimation.MoveWood;
import static java.util.Objects.requireNonNull;

/**
 * Defines a catalog of animations as laid out in land gatherer texture atlas
 * files.
 *
 * @author Blair Butterworth
 */
public class LandGathererAnimations extends AnimationCatalog
{
    private static final GridPoint2 SIZE = new GridPoint2(72, 72);

    public LandGathererAnimations(GathererAssets assets) {
        this(assets.getBaseTexture(),
            assets.getMoveWithGoldTexture(),
            assets.getMoveWithWoodTexture(),
            assets.getDecomposeTexture(),
            SIZE);
    }

    public LandGathererAnimations(Texture base, Texture gold, Texture wood, Texture decompose, GridPoint2 size) {
        super(11);

        requireNonNull(base);
        requireNonNull(gold);
        requireNonNull(wood);
        requireNonNull(decompose);

        idle(base, gold, wood, size);
        move(base, gold, wood, size);
        attack(base, size);
        chopping(base, size);
        death(base, decompose, size);
    }

    private void idle(Texture base, Texture gold, Texture wood, GridPoint2 size) {
        alias(Idle, IdleBasic);

        animation(IdleBasic)
            .withTexture(base)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(10f)
            .looping();

        animation(IdleGold)
            .withTexture(gold)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(10f)
            .looping();

        animation(IdleWood)
            .withTexture(wood)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(10f)
            .looping();
    }

    private void move(Texture base, Texture gold, Texture wood, GridPoint2 size) {
        alias(Move, MoveBasic);

        animation(MoveBasic)
            .withTexture(base)
            .withSequence(0, 5)
            .withSize(size)
            .withInterval(0.10f)
            .looping();

        animation(MoveGold)
            .withTexture(gold)
            .withSequence(0, 5)
            .withSize(size)
            .withInterval(0.10f)
            .looping();

        animation(MoveWood)
            .withTexture(wood)
            .withSequence(0, 5)
            .withSize(size)
            .withInterval(0.10f)
            .looping();
    }

    private void attack(Texture base, GridPoint2 size) {
        animation(Attack)
            .withTexture(base)
            .withSequence(size.y * 5, 4)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(0.15f)
            .notLooping();
    }

    private void chopping(Texture base, GridPoint2 size) {
        animation(GatherWood)
            .withTexture(base)
            .withSequence(size.y * 5, 4)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(0.15f)
            .looping();
    }

    private void death(Texture base, Texture decompose, GridPoint2 size) {
        sequence(Death)
            .element()
                .withTexture(base)
                .withSequence(size.y * 9, 3)
                .withSize(size)
                .withInterval(0.15f)
            .element()
                .withTexture(decompose)
                .withSequence(0, 6)
                .withSize(size)
                .withInterval(5f);
    }
}
