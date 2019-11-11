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
import com.evilbird.warcraft.item.common.upgrade.UpgradeSeries;
import com.evilbird.warcraft.item.common.value.UpgradeValue;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantFactoryBase;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.item.common.capability.MovementCapability.Water;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.NavalDefence;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Cannonball;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenDestroyer;

/**
 * Instances of this factory create Elven Destroyers, Human entry level ships.
 *
 * @author Blair Butterworth
 */
public class ElvenDestroyerFactory extends CombatantFactoryBase
{
    @Inject
    public ElvenDestroyerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public ElvenDestroyerFactory(AssetManager manager) {
        super(manager, ElvenDestroyer);
    }

    @Override
    public Combatant get(Identifier type) {
        RangedCombatant result = builder.newRangedCombatant();
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
