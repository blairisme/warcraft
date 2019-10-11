/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.animations;

import com.evilbird.warcraft.item.common.production.ProductionTimes;
import com.evilbird.warcraft.item.unit.building.BuildingAssets;

import static com.evilbird.warcraft.item.unit.UnitAnimation.Attack;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;

/**
 * Defines a catalog of animations as laid out in tower texture atlas files.
 * Animations are provided for construction, destruction, pre-construction,
 * post-construction and attack.
 *
 * @author Blair Butterworth
 */
public class TowerAnimations extends BuildingAnimations
{
    public TowerAnimations(BuildingAssets assets, ProductionTimes production) {
        super(assets, production);
        alias(Attack, Idle);
    }
}
