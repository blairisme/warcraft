/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.building;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.building.human.HumanBuildingFactory;
import com.evilbird.warcraft.object.unit.building.neutral.NeutralBuildingFactory;
import com.evilbird.warcraft.object.unit.building.orc.OrcBuildingFactory;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Building Buildings}, a
 * {@link Unit} specialization that provides the user the ability to train
 * combatants and research upgrades.
 *
 * @author Blair Butterworth
 */
public class BuildingFactory extends GameFactorySet<Building>
{
    @Inject
    public BuildingFactory(
        HumanBuildingFactory humanBuildingFactory,
        NeutralBuildingFactory neutralBuildingFactory,
        OrcBuildingFactory orcBuildingFactory)
    {
        addProvider(humanBuildingFactory);
        addProvider(neutralBuildingFactory);
        addProvider(orcBuildingFactory);
    }
}