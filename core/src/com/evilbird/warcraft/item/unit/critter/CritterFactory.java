/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.critter;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.critter.neutral.BoarFactory;
import com.evilbird.warcraft.item.unit.critter.neutral.SealFactory;
import com.evilbird.warcraft.item.unit.critter.neutral.SheepFactory;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Critter Critters}, animals that
 * inhabit the game world.
 *
 * @author Blair Butterworth
 */
public class CritterFactory extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public CritterFactory(BoarFactory boarFactory, SealFactory sealFactory, SheepFactory sheepFactory) {
        addProvider(UnitType.Boar, boarFactory);
        addProvider(UnitType.Seal, sealFactory);
        addProvider(UnitType.Sheep, sheepFactory);
    }
}
