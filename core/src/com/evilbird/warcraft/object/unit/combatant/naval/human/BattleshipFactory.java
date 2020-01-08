/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.naval.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.common.value.UpgradeValue;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;
import com.evilbird.warcraft.object.unit.combatant.naval.NavalUnitFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.NavalDamage;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.NavalDefence;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Water;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.projectile.ProjectileType.FlamingCannonball;
import static com.evilbird.warcraft.object.unit.UnitType.Battleship;

/**
 * Instances of this factory create Battleships, Human heavy armoured ships.
 *
 * @author Blair Butterworth
 */
public class BattleshipFactory extends NavalUnitFactory
{
    @Inject
    public BattleshipFactory(Device device) {
        this(device.getAssetStorage());
    }

    public BattleshipFactory(AssetManager manager) {
        super(manager, Battleship);
    }

    @Override
    public RangedCombatant get(Identifier type) {
        RangedCombatant result = builder.build();
        result.setAttackSpeed(1.5f);
        result.setAttackRange(tiles(7));
        result.setArmour(new UpgradeValue(NavalDefence, 10, 20, 30));
        result.setBasicDamage(new UpgradeValue(NavalDamage, 50, 60, 70));
        result.setPiercingDamage(10);
        result.setHealth(60);
        result.setHealthMaximum(60);
        result.setIdentifier(objectIdentifier("Battleship", result));
        result.setMovementSpeed(8 * 7);
        result.setMovementCapability(Water);
        result.setSight(tiles(5));
        result.setType(Battleship);
        result.setProjectileType(FlamingCannonball);
        return result;
    }
}