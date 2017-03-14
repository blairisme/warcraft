package com.evilbird.warcraft.item.unit.resource;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.resource.goldmine.GoldMineProvider;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ResourceProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public ResourceProvider(GoldMineProvider goldMineProvider)
    {
        addProvider(UnitType.GoldMine, goldMineProvider);
    }
}
