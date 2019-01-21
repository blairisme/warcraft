/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.resource;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.resource.goldmine.GoldMineProvider;
import com.evilbird.warcraft.item.unit.resource.oilpatch.OilPatchProvider;

import javax.inject.Inject;

public class ResourceProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public ResourceProvider(GoldMineProvider goldMineProvider, OilPatchProvider oilPatchProvider)
    {
        addProvider(UnitType.GoldMine, goldMineProvider);
        addProvider(UnitType.OilPatch, oilPatchProvider);
    }
}
