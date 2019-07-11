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
import com.evilbird.warcraft.item.ui.effect.EffectFactory;
import com.evilbird.warcraft.item.layer.LayerFactory;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import com.evilbird.warcraft.item.projectile.ProjectileFactory;
import com.evilbird.warcraft.item.projectile.ProjectileType;
import com.evilbird.warcraft.item.ui.hud.HudFactory;
import com.evilbird.warcraft.item.ui.hud.HudType;
import com.evilbird.warcraft.item.ui.placement.PlaceholderFactory;
import com.evilbird.warcraft.item.ui.placement.PlaceholderType;
import com.evilbird.warcraft.item.unit.UnitFactory;

import javax.inject.Inject;

/**
 * Instances of this {@link ItemFactory factory} create {@link Item} instances
 * tailored for Warcraft 2.
 *
 * @author Blair Butterworth
 */
public class WarcraftItemFactory implements ItemFactory
{
    private IdentifiedAssetProviderSet<Item> providers;

    @Inject
    public WarcraftItemFactory(
        DataProvider dataProvider,
        LayerFactory layerFactory,
        UnitFactory unitFactory,
        EffectFactory effectFactory,
        HudFactory hudFactory,
        PlaceholderFactory placeholderFactory,
        ProjectileFactory projectileFactory)
    {
        providers = new IdentifiedAssetProviderSet<>();
        providers.addProvider(unitFactory);
        providers.addProvider(dataProvider);
        providers.addProvider(effectFactory);
        providers.addProvider(PlaceholderType.class, placeholderFactory);
        providers.addProvider(HudType.class, hudFactory);
        providers.addProvider(LayerIdentifier.class, layerFactory);
        providers.addProvider(ProjectileType.class, projectileFactory);
    }

    @Override
    public void load() {
        providers.load();
    }

    @Override
    public Item newItem(ItemType type) {
        return providers.get(type);
    }
}
