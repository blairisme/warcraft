/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.building.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.warcraft.object.common.production.ProductionTimes;
import com.evilbird.warcraft.object.unit.building.BuildingAssets;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;

/**
 * Defines a catalog of animations as laid out in Goblin Alchemist texture
 * atlas files. Animations are provided for construction, destruction,
 * pre-construction and post-construction.
 *
 * @author Blair Butterworth
 */
public class GoblinAlchemistAnimations extends BuildingAnimations
{
    public GoblinAlchemistAnimations(BuildingAssets assets, ProductionTimes production) {
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
