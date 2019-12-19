/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.object.badge.BadgeFactory;
import com.evilbird.warcraft.object.badge.BadgeType;
import com.evilbird.warcraft.object.data.DataFactory;
import com.evilbird.warcraft.object.display.UserInterfaceFactory;
import com.evilbird.warcraft.object.display.UserInterfaceView;
import com.evilbird.warcraft.object.effect.EffectFactory;
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.layer.LayerFactory;
import com.evilbird.warcraft.object.layer.LayerIdentifier;
import com.evilbird.warcraft.object.projectile.ProjectileFactory;
import com.evilbird.warcraft.object.projectile.ProjectileType;
import com.evilbird.warcraft.object.selector.SelectorFactory;
import com.evilbird.warcraft.object.selector.SelectorType;
import com.evilbird.warcraft.object.unit.UnitFactory;

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
        UserInterfaceFactory userInterfaceFactory,
        LayerFactory layerFactory,
        ProjectileFactory projectileFactory,
        SelectorFactory selectorFactory,
        UnitFactory unitFactory)
    {
        addProvider(unitFactory);
        addProvider(dataFactory);
        addProvider(BadgeType.class, badgeFactory);
        addProvider(EffectType.class, effectFactory);
        addProvider(UserInterfaceView.class, userInterfaceFactory);
        addProvider(LayerIdentifier.class, layerFactory);
        addProvider(ProjectileType.class, projectileFactory);
        addProvider(SelectorType.class, selectorFactory);
    }
}
