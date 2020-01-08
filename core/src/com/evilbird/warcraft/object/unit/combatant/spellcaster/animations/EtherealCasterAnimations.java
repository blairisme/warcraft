/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.spellcaster.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Attack;
import static com.evilbird.warcraft.object.unit.UnitAnimation.CastSpell;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;
import static java.util.Objects.requireNonNull;

/**
 * Defines a catalog of spell caster animations as laid out in wizard texture
 * atlas files, used for Human Mages and Orcish Death Knights.
 *
 * @author Blair Butterworth
 */
public class EtherealCasterAnimations extends AnimationCatalog
{
    private static final GridPoint2 SIZE = new GridPoint2(72, 72);

    /**
     * Creates a new instance of this class given a {@link CombatantAssets}
     * bundle, containing the assets required by ethereal spell caster
     * animations. Animations contained in this catalog will use a default
     * unit size of 72 x 72.
     *
     * @param assets an asset bundle, which must contain a valid base texture.
     *
     * @throws NullPointerException if the given asset bundle is {@code null}.
     */
    public EtherealCasterAnimations(CombatantAssets assets) {
        this(assets.getBaseTexture());
    }

    public EtherealCasterAnimations(Texture general) {
        this(general, SIZE);
    }

    public EtherealCasterAnimations(Texture base, GridPoint2 size) {
        super(5);

        requireNonNull(base);
        requireNonNull(size);

        idle(base, size);
        move(base, size);
        death(base, size);
        attack(base, size);
    }

    private void attack(Texture base, GridPoint2 size) {
        alias(CastSpell, Attack);
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
            .withBlankFrame()
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
