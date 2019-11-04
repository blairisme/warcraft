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
import static com.evilbird.warcraft.item.unit.UnitType.RuneTrap;

/**
 * Instances of this class create {@link ConjuredObject Rune Traps}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class RuneTrapFactory extends ConjuredFactoryBase
{
    @Inject
    public RuneTrapFactory(Device device) {
        this(device.getAssetStorage());
    }

    public RuneTrapFactory(AssetManager manager) {
        super(manager, RuneTrap);
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
