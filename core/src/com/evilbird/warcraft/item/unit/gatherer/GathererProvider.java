package com.evilbird.warcraft.item.unit.gatherer;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.gatherer.human.PeasantProvider;

import javax.inject.Inject;

/**
 * Created by blair on 21/09/2017.
 */

public class GathererProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public GathererProvider(
        PeasantProvider peasantProvider)
    {
        super();
        addProvider(UnitType.Peasant, peasantProvider);
    }
}
