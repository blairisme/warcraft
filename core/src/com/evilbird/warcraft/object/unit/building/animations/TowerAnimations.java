/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.building.animations;

import com.evilbird.warcraft.object.common.production.ProductionTimes;
import com.evilbird.warcraft.object.unit.building.BuildingAssets;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Attack;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;

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
