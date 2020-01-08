/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.flying.neutral;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnitAssets;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Attack;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;
import static java.util.Objects.requireNonNull;

/**
 * Defines a catalog of daemon animations as laid out in daemon
 * texture atlas files.
 *
 * @author Blair Butterworth
 */
public class DaemonAnimations extends AnimationCatalog
{
    private static final GridPoint2 SIZE = new GridPoint2(72, 72);

    public DaemonAnimations(FlyingUnitAssets assets) {
        this(assets.getBaseTexture(), assets.getDecomposeTexture());
    }

    public DaemonAnimations(Texture general, Texture decompose) {
        this(general, decompose, SIZE);
    }

    public DaemonAnimations(Texture base, Texture decompose, GridPoint2 size) {
        super(4);

        requireNonNull(base);
        requireNonNull(decompose);
        requireNonNull(size);

        attack(base, size);
        death(base, size);
        idleAndMove(base, size);
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

    private void idleAndMove(Texture base, GridPoint2 size) {
        alias(Move, Idle);
        animation(Idle)
            .withTexture(base)
            .withSequence(0, 5)
            .withSize(size)
            .withInterval(0.12f)
            .looping();
    }
}
