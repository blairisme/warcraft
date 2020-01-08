/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.building.neutral;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;

import javax.inject.Inject;

/**
 * Instances of this factory create Neutral {@link Building Buildings}, a
 * {@link Unit} specialization that provides the user the ability to train
 * combatants and research upgrades.
 *
 * @author Blair Butterworth
 */
public class NeutralBuildingFactory extends GameFactorySet<Building>
{
    @Inject
    public NeutralBuildingFactory(
        CircleOfPowerFactory circleOfPowerFactory,
        DarkPortalFactory darkPortalFactory,
        RunestoneFactory runestoneFactory)
    {
        addProvider(UnitType.CircleOfPower, circleOfPowerFactory);
        addProvider(UnitType.DarkPortal, darkPortalFactory);
        addProvider(UnitType.Runestone, runestoneFactory);
    }
}
