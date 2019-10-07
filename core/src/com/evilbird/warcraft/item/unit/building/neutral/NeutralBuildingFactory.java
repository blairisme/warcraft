/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.neutral;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

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
        DarkPortalFactory darkPortalFactory)
    {
        addProvider(UnitType.CircleOfPower, circleOfPowerFactory);
        addProvider(UnitType.DarkPortal, darkPortalFactory);
    }
}
