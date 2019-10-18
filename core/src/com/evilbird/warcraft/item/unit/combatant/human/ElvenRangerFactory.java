/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.common.upgrade.UpgradeSequence;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.RangedSight;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenRanger;

/**
 * Instances of this factory create Elven Rangers, an improved version of an
 * Elven Archer.
 *
 * @author Blair Butterworth
 */
public class ElvenRangerFactory extends ElvenArcherFactory
{
    @Inject
    public ElvenRangerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public ElvenRangerFactory(AssetManager manager) {
        super(manager);
    }

    @Override
    public Combatant get(Identifier type) {
        Combatant result = super.get(type);
        result.setHealth(50);
        result.setHealthMaximum(50);
        result.setIdentifier(objectIdentifier("ElvenRanger", result));
        result.setType(ElvenRanger);
        result.setSight(new UpgradeSequence<>(RangedSight, tiles(5), tiles(9)));
        return result;
    }
}