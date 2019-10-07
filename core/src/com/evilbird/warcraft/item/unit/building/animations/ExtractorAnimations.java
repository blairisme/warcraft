/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.warcraft.item.common.production.ProductionTimes;
import com.evilbird.warcraft.item.unit.building.BuildingAssets;

import static com.evilbird.warcraft.item.unit.UnitAnimation.Gathering;

/**
 * Defines a catalog of animations as laid out in resource extractor building
 * texture atlas files. Animations are provided for construction, destruction,
 * pre-construction, post-construction and resource extraction.
 *
 * @author Blair Butterworth
 */
public class ExtractorAnimations extends BuildingAnimations
{
    public ExtractorAnimations(BuildingAssets assets, ProductionTimes production) {
        super(assets, production);
        gatherOil(assets.getBaseTexture(), assets.getSize());
    }

    private void gatherOil(Texture base, GridPoint2 size) {
        animation(Gathering)
            .withTexture(base)
            .withSequence(size.y * 2, 1)
            .withSize(size)
            .withInterval(10f)
            .singleDirection()
            .looping();
    }
}
