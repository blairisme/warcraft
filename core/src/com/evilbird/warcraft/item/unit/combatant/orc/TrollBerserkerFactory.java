/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.item.unit.UnitType.TrollBerserker;

/**
 * Instances of this factory create Troll Berserkers, an upgraded version of a
 * Troll Axethrower.
 *
 * @author Blair Butterworth
 */
public class TrollBerserkerFactory extends TrollAxethrowerFactory
{
    @Inject
    public TrollBerserkerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public TrollBerserkerFactory(AssetManager manager) {
        super(manager);
    }

    @Override
    public Combatant get(Identifier type) {
        RangedCombatant result = (RangedCombatant)super.get(type);
        result.setHealth(50);
        result.setHealthMaximum(50);
        result.setIdentifier(objectIdentifier("TrollBerserker", result));
        result.setSight(tiles(6));
        result.setType(TrollBerserker);
        return result;
    }
}