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
import com.evilbird.warcraft.data.upgrade.UpgradeSeries;
import com.evilbird.warcraft.object.common.value.UpgradeValue;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;
import com.evilbird.warcraft.object.unit.combatant.naval.NavalUnitFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.NavalDefence;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Water;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Cannonball;
import static com.evilbird.warcraft.object.unit.UnitType.ElvenDestroyer;

/**
 * Instances of this factory create Elven Destroyers, Human entry level ships.
 *
 * @author Blair Butterworth
 */
public class ElvenDestroyerFactory extends NavalUnitFactory
{
    @Inject
    public ElvenDestroyerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public ElvenDestroyerFactory(AssetManager manager) {
        super(manager, ElvenDestroyer);
    }

    @Override
    public RangedCombatant get(Identifier type) {
        RangedCombatant result = builder.build();
        result.setAttackSpeed(1.5f);
        result.setAttackRange(tiles(4));
        result.setArmour(new UpgradeValue(NavalDefence, 10, 20, 30));
        result.setBasicDamage(new UpgradeValue(UpgradeSeries.NavalDamage, 35, 37, 39));
        result.setPiercingDamage(2);
        result.setHealth(100);
        result.setHealthMaximum(100);
        result.setIdentifier(objectIdentifier("ElvenDestroyer", result));
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Water);
        result.setSight(tiles(8));
        result.setType(ElvenDestroyer);
        result.setProjectileType(Cannonball);
        return result;
    }
}
