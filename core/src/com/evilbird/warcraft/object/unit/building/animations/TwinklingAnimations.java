/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.building.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.warcraft.object.common.production.ProductionTimes;
import com.evilbird.warcraft.object.unit.building.BuildingAssets;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;

/**
 * Defines a catalog of animations as laid out in Goblin Alchemist or Church
 * texture atlas files. Animations are provided for construction, destruction,
 * pre-construction and post-construction.
 *
 * @author Blair Butterworth
 */
public class TwinklingAnimations extends BuildingAnimations
{
    public TwinklingAnimations(BuildingAssets assets, ProductionTimes production) {
        super(assets, production);
        idle(assets.getBaseTexture(), assets.getSize());
    }

    private void idle(Texture base, GridPoint2 size) {
        if (base.getHeight() > 200) {
            animation(Idle)
                .withTexture(base)
                .withSequence(0, 3)
                .withSize(size)
                .withInterval(0.5f)
                .singleDirection()
                .looping();
        }
    }
}
