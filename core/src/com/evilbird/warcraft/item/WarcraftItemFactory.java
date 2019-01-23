/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.item.data.DataProvider;
import com.evilbird.warcraft.item.effect.EffectProvider;
import com.evilbird.warcraft.item.hud.HudControlProvider;
import com.evilbird.warcraft.item.layer.LayerProvider;
import com.evilbird.warcraft.item.placeholder.PlaceholderProvider;
import com.evilbird.warcraft.item.unit.UnitProvider;

import javax.inject.Inject;

public class WarcraftItemFactory implements ItemFactory
{
    private IdentifiedAssetProviderSet<Item> providers;

    @Inject
    public WarcraftItemFactory(
        DataProvider dataProvider,
        HudControlProvider hudProvider,
        LayerProvider layerProvider,
        UnitProvider unitProvider,
        EffectProvider effectProvider,
        PlaceholderProvider buildingSiteProvider)
    {
        providers = new IdentifiedAssetProviderSet<>();
        providers.addProvider(unitProvider);
        providers.addProvider(hudProvider);
        providers.addProvider(layerProvider);
        providers.addProvider(dataProvider);
        providers.addProvider(effectProvider);
        providers.addProvider(buildingSiteProvider);
    }

    @Override
    public void load() {
        providers.load();
    }

    @Override
    public Item newItem(ItemType type) {
        Item result = providers.get(type);
        if (result == null) {
            throw new IllegalArgumentException();
        }
        return result;
    }
}
