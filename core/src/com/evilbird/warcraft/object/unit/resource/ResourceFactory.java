/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
