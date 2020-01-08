/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.flying.human;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnitAssets;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;
import static java.util.Objects.requireNonNull;

/**
 * Defines a catalog of animations as laid out in Gnomish flying machine
 * texture atlas files.
 *
 * @author Blair Butterworth
 */
public class GnomishFlyingMachineAnimations extends AnimationCatalog
{
    public GnomishFlyingMachineAnimations(FlyingUnitAssets assets) {
        this(assets.getBaseTexture(), assets.getSize());
    }

    public GnomishFlyingMachineAnimations(Texture base, GridPoint2 size) {
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
            .withBlankFrame()
            .withSize(size)
            .withInterval(0.15f)
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
