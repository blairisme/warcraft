/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.siege.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.common.value.UpgradeValue;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;
import com.evilbird.warcraft.object.unit.combatant.siege.SiegeUnitFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.SiegeDamage;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Land;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.projectile.ProjectileType.FlamingRock;
import static com.evilbird.warcraft.object.unit.UnitType.Catapult;

/**
 * Instances of this factory create Catapults, Orcish siege weapons.
 *
 * @author Blair Butterworth
 */
public class CatapultFactory extends SiegeUnitFactory
{
    @Inject
    public CatapultFactory(Device device) {
        this(device.getAssetStorage());
    }

    public CatapultFactory(AssetManager manager) {
        super(manager, Catapult);
    }

    @Override
    public RangedCombatant get(Identifier type) {
        RangedCombatant result = builder.build();
        result.setAttackSpeed(4);
        result.setArmour(0);
        result.setBasicDamage(new UpgradeValue(SiegeDamage, 80, 95, 110));
        result.setPiercingDamage(25);
        result.setHealth(110);
        result.setHealthMaximum(110);
        result.setIdentifier(objectIdentifier("Catapult", result));
        result.setMovementSpeed(8 * 5);
        result.setMovementCapability(Land);
        result.setAttackRange(tiles(8));
        result.setSight(tiles(9));
        result.setType(Catapult);
        result.setProjectileType(FlamingRock);
        return result;
    }
}