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

import static com.evilbird.warcraft.item.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Move;
import static java.util.Objects.requireNonNull;

/**
 * Defines a catalog of animations as laid out in non-attacking flying unit
 * texture atlas files.
 *
 * @author Blair Butterworth
 */
public class ScoutAnimations extends AnimationCatalog
{
    public ScoutAnimations(CombatantAssets assets) {
        this(assets.getBaseTexture(), assets.getSize());
    }

    public ScoutAnimations(Texture base, GridPoint2 size) {
        super(3);

        requireNonNull(base);
        requireNonNull(size);

        death(base, size);
        idleAndMove(base, size);
    }

    private void death(Texture base, GridPoint2 size) {
        animation(Death)
            .withTexture(base)
            .withSequence(size.y * 2, 2)
            .withSize(size)
            .withInterval(0.5f)
            .notLooping();
    }

    private void idleAndMove(Texture base, GridPoint2 size) {
        alias(Move, Idle);
        animation(Idle)
            .withTexture(base)
            .withSequence(0, 2)
            .withSize(size)
            .withInterval(0.10f)
            .looping();
    }
}
