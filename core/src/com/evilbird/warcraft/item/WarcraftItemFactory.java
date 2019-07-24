/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item;

import com.evilbird.engine.game.GameFactoryComposite;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.item.data.DataFactory;
import com.evilbird.warcraft.item.layer.LayerFactory;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import com.evilbird.warcraft.item.projectile.ProjectileFactory;
import com.evilbird.warcraft.item.projectile.ProjectileType;
import com.evilbird.warcraft.item.ui.confirmation.ConfirmFactory;
import com.evilbird.warcraft.item.ui.confirmation.ConfirmType;
import com.evilbird.warcraft.item.ui.display.HudFactory;
import com.evilbird.warcraft.item.ui.display.HudType;
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
public class WarcraftItemFactory extends GameFactoryComposite<Item> implements ItemFactory
{
    @Inject
    public WarcraftItemFactory(
        DataFactory dataFactory,
        LayerFactory layerFactory,
        UnitFactory unitFactory,
        ConfirmFactory confirmFactory,
        HudFactory hudFactory,
        PlaceholderFactory placeholderFactory,
        ProjectileFactory projectileFactory)
    {
        addProvider(unitFactory);
        addProvider(dataFactory);
        addProvider(ConfirmType.class, confirmFactory);
        addProvider(PlaceholderType.class, placeholderFactory);
        addProvider(HudType.class, hudFactory);
        addProvider(LayerIdentifier.class, layerFactory);
        addProvider(ProjectileType.class, projectileFactory);
    }
}
