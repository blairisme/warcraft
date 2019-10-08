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
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Move;
import static java.util.Objects.requireNonNull;

/**
 * Defines a catalog of animations as laid out in siege unit texture atlas
 * files.
 *
 * @author Blair Butterworth
 */
public class SiegeAnimations extends AnimationCatalog
{
    public SiegeAnimations(CombatantAssets assets) {
        this(assets.getBaseTexture(), assets.getSize());
    }

    public SiegeAnimations(Texture base, GridPoint2 size) {
        super(3);

        requireNonNull(base);
        requireNonNull(size);

        attack(base, size);
        idle(base, size);
        move(base, size);
    }

    private void attack(Texture base, GridPoint2 size) {
        animation(Attack)
            .withTexture(base)
            .withSequence(size.y, 3)
            .withSize(size)
            .withInterval(0.15f)
            .looping();
    }

    private void idle(Texture base, GridPoint2 size) {
        animation(Idle)
            .withTexture(base)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(1f)
            .looping();
    }

    private void move(Texture base, GridPoint2 size) {
        animation(Move)
            .withTexture(base)
            .withSequence(0, 2)
            .withSize(size)
            .withInterval(0.15f)
            .looping();
    }
}
