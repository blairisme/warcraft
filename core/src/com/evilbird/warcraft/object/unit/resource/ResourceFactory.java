/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.resource;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.resource.neutral.GoldMineFactory;
import com.evilbird.warcraft.object.unit.resource.neutral.OilPatchFactory;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Resource Resources}, {@link Unit}
 * specializations from which resources of different varieties can be obtained.
 *
 * @author Blair Butterworth
 */
public class ResourceFactory extends GameFactorySet<Resource>
{
    @Inject
    public ResourceFactory(GoldMineFactory goldMineFactory, OilPatchFactory oilPatchFactory) {
        addProvider(UnitType.GoldMine, goldMineFactory);
        addProvider(UnitType.OilPatch, oilPatchFactory);
    }
}
