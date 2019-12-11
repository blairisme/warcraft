/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.siege.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.common.value.UpgradeValue;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;
import com.evilbird.warcraft.object.unit.combatant.siege.SiegeUnitFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Land;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.common.upgrade.UpgradeSeries.SiegeDamage;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Bolt;
import static com.evilbird.warcraft.object.unit.UnitType.Ballista;

/**
 * Instances of this factory create Ballistas, Human siege weapons.
 *
 * @author Blair Butterworth
 */
public class BallistaFactory extends SiegeUnitFactory
{
    @Inject
    public BallistaFactory(Device device) {
        this(device.getAssetStorage());
    }

    public BallistaFactory(AssetManager manager) {
        super(manager, Ballista);
    }

    @Override
    public RangedCombatant get(Identifier type) {
        RangedCombatant result = builder.build();
        result.setAttackSpeed(3);
        result.setAttackRange(tiles(8));
        result.setArmour(0);
        result.setPiercingDamage(25);
        result.setBasicDamage(new UpgradeValue(SiegeDamage, 80, 95, 110));
        result.setHealth(110);
        result.setHealthMaximum(110);
        result.setIdentifier(objectIdentifier("Ballista", result));
        result.setMovementSpeed(8 * 5);
        result.setMovementCapability(Land);
        result.setSight(tiles(9));
        result.setType(Ballista);
        result.setProjectileType(Bolt);
        return result;
    }
}