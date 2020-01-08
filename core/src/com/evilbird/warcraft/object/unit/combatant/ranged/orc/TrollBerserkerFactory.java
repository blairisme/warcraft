/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.ranged.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.TrollBerserker;

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
    public RangedCombatant get(Identifier type) {
        RangedCombatant result = super.get(type);
        result.setHealth(50);
        result.setHealthMaximum(50);
        result.setIdentifier(objectIdentifier("TrollBerserker", result));
        result.setSight(tiles(6));
        result.setType(TrollBerserker);
        return result;
    }
}