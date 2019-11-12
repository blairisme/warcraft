/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.item.badge.BadgeFactory;
import com.evilbird.warcraft.item.badge.BadgeType;
import com.evilbird.warcraft.item.data.DataFactory;
import com.evilbird.warcraft.item.display.HudFactory;
import com.evilbird.warcraft.item.display.HudType;
import com.evilbird.warcraft.item.effect.EffectFactory;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.layer.LayerFactory;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import com.evilbird.warcraft.item.projectile.ProjectileFactory;
import com.evilbird.warcraft.item.projectile.ProjectileType;
import com.evilbird.warcraft.item.selector.SelectorFactory;
import com.evilbird.warcraft.item.selector.SelectorType;
import com.evilbird.warcraft.item.unit.UnitFactory;

import javax.inject.Inject;

/**
 * Instances of this {@link GameObjectFactory factory} create
 * {@link GameObject} instances tailored for Warcraft 2.
 *
 * @author Blair Butterworth
 */
public class WarcraftObjectFactory extends GameFactorySet<GameObject> implements GameObjectFactory
{
    @Inject
    public WarcraftObjectFactory(
        BadgeFactory badgeFactory,
        DataFactory dataFactory,
        EffectFactory effectFactory,
        HudFactory hudFactory,
        LayerFactory layerFactory,
        ProjectileFactory projectileFactory,
        SelectorFactory selectorFactory,
        UnitFactory unitFactory)
    {
        addProvider(unitFactory);
        addProvider(dataFactory);
        addProvider(BadgeType.class, badgeFactory);
        addProvider(EffectType.class, effectFactory);
        addProvider(HudType.class, hudFactory);
        addProvider(LayerIdentifier.class, layerFactory);
        addProvider(ProjectileType.class, projectileFactory);
        addProvider(SelectorType.class, selectorFactory);
    }
}
