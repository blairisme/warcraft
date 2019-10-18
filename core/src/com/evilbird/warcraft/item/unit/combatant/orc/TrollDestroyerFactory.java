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
import com.evilbird.warcraft.item.common.upgrade.UpgradableValue;
import com.evilbird.warcraft.item.common.upgrade.UpgradeSeries;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantFactoryBase;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.state.MovementCapability.Water;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Cannonball;
import static com.evilbird.warcraft.item.unit.UnitType.TrollDestroyer;

/**
 * Instances of this factory create Troll Destroyers, Orcish entry level ships.
 *
 * @author Blair Butterworth
 */
public class TrollDestroyerFactory extends CombatantFactoryBase
{
    @Inject
    public TrollDestroyerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public TrollDestroyerFactory(AssetManager manager) {
        super(manager, TrollDestroyer);
    }

    @Override
    public Combatant get(Identifier type) {
        RangedCombatant result = builder.newRangedCombatant();
        result.setAttackSpeed(1.5f);
        result.setArmour(10);
        result.setPiercingDamage(2);
        result.setBasicDamage(new UpgradableValue(UpgradeSeries.NavalDamage, 35, 37, 39));
        result.setHealth(100);
        result.setHealthMaximum(100);
        result.setIdentifier(objectIdentifier("TrollDestroyer", result));
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Water);
        result.setAttackRange(tiles(4));
        result.setSight(tiles(8));
        result.setType(TrollDestroyer);
        result.setProjectileType(Cannonball);
        return result;
    }
}
