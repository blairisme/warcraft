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
 * Defines a catalog of spell caster animations as laid out in spell caster
 * texture atlas files.
 *
 * @author Blair Butterworth
 */
public class SpellCasterAnimations extends AnimationCatalog
{
    private static final GridPoint2 SIZE = new GridPoint2(72, 72);

    public SpellCasterAnimations(CombatantAssets assets) {
        this(assets.getBaseTexture(), assets.getDecomposeTexture());
    }

    public SpellCasterAnimations(Texture general, Texture decompose) {
        this(general, decompose, SIZE);
    }

    public SpellCasterAnimations(Texture base, Texture decompose, GridPoint2 size) {
        super(4);

        requireNonNull(base);
        requireNonNull(decompose);
        requireNonNull(size);

        idle(base, size);
        move(base, size);
        death(base, size);
        attack(base, size);
    }

    private void attack(Texture base, GridPoint2 size) {
        animation(Attack)
            .withTexture(base)
            .withSequence(size.y * 5, 5)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(0.15f)
            .notLooping();
    }

    private void death(Texture base, GridPoint2 size) {
        animation(Death)
            .withTexture(base)
            .withSequence(size.y * 10, (base.getHeight() / size.y) - 10)
            .withSize(size)
            .withInterval(0.15f)
            .notLooping();
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
}
