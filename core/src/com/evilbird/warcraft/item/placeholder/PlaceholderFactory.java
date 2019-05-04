/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.placeholder;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.placeholder.human.BarracksPlaceholderFactory;
import com.evilbird.warcraft.item.placeholder.human.FarmPlaceholderProvider;
import com.evilbird.warcraft.item.placeholder.human.TownHallPlaceholderProvider;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Placeholder Placeholders}, visual
 * representations of a buildings before their construction.
 *
 * @author Blair Butterworth
 */
public class PlaceholderFactory extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public PlaceholderFactory(
        BarracksPlaceholderFactory barracksBuildSiteProvider,
        FarmPlaceholderProvider farmBuildSiteProvider,
        TownHallPlaceholderProvider townHallBuildSiteProvider)
    {
        addProvider(PlaceholderType.BarracksPlaceholder, barracksBuildSiteProvider);
        addProvider(PlaceholderType.FarmPlaceholder, farmBuildSiteProvider);
        addProvider(PlaceholderType.TownHallPlaceholder, townHallBuildSiteProvider);
    }
}
