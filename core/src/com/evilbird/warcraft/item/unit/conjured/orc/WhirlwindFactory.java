/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.conjured.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.unit.conjured.ConjuredFactoryBase;
import com.evilbird.warcraft.item.unit.conjured.ConjuredObject;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.unit.UnitType.Whirlwind;

/**
 * Instances of this class create a Whirlwind {@link ConjuredObject},
 * loading the necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class WhirlwindFactory extends ConjuredFactoryBase
{
    @Inject
    public WhirlwindFactory(Device device) {
        this(device.getAssetStorage());
    }

    public WhirlwindFactory(AssetManager manager) {
        super(manager, Whirlwind);
    }

    @Override
    public ConjuredObject get(Identifier type) {
        ConjuredObject result = builder.build();
        result.setAttackSpeed(3);
        result.setAttackRange(tiles(2));
        result.setBasicDamage(20);
        result.setPiercingDamage(5);
        return result;
    }
}
