/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.resource;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.resource.goldmine.GoldMineFactory;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Resource Resources}, {@link Unit}
 * specializations from which resources of different varieties can be obtained.
 *
 * @author Blair Butterworth
 */
public class ResourceFactory extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public ResourceFactory(GoldMineFactory goldMineFactory) {
        addProvider(UnitType.GoldMine, goldMineFactory);
    }
}
