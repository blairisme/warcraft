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
import static com.evilbird.warcraft.item.unit.UnitAnimation.Decompose;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Move;
import static java.util.Objects.requireNonNull;

/**
 * Defines a catalog of animations as laid out in ranged unit texture atlas
 * files.
 *
 * @author Blair Butterworth
 */
public class RangedAnimations extends AnimationCatalog
{
    private static final GridPoint2 SIZE = new GridPoint2(72, 72);

    public RangedAnimations(CombatantAssets assets) {
        this(assets.getBaseTexture(), assets.getDecomposeTexture());
    }

    public RangedAnimations(Texture general, Texture decompose) {
        this(general, decompose, SIZE);
    }

    public RangedAnimations(Texture base, Texture decompose, GridPoint2 size) {
        super(5);

        requireNonNull(base);
        requireNonNull(decompose);
        requireNonNull(size);

        idle(base, size);
        move(base, size);
        death(base, size);
        decompose(decompose, size);
        attack(base, size);
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
            .withSequence(size.y * 9, 3)
            .withSize(size)
            .withInterval(0.15f)
            .notLooping();
    }

    private void decompose(Texture decompose, GridPoint2 size) {
        animation(Decompose)
            .withTexture(decompose)
            .withSequence(0, 6)
            .withSize(size)
            .withInterval(2f)
            .notLooping();
    }

    private void attack(Texture base, GridPoint2 size) {
        animation(Attack)
            .withTexture(base)
            .withSequence(size.y * 5, 2)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(0.15f)
            .notLooping();
    }
}
