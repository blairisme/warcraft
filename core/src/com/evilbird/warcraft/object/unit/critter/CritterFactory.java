/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.critter;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.critter.neutral.BoarFactory;
import com.evilbird.warcraft.object.unit.critter.neutral.SealFactory;
import com.evilbird.warcraft.object.unit.critter.neutral.SheepFactory;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Critter Critters}, animals that
 * inhabit the game world.
 *
 * @author Blair Butterworth
 */
public class CritterFactory extends GameFactorySet<Critter>
{
    @Inject
    public CritterFactory(BoarFactory boarFactory, SealFactory sealFactory, SheepFactory sheepFactory) {
        addProvider(UnitType.Boar, boarFactory);
        addProvider(UnitType.Seal, sealFactory);
        addProvider(UnitType.Sheep, sheepFactory);
    }
}
