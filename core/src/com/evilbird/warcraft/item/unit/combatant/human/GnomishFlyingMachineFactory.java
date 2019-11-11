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
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantFactoryBase;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.item.common.capability.MovementCapability.Air;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Arrow;
import static com.evilbird.warcraft.item.unit.UnitType.GnomishFlyingMachine;

/**
 * Instances of this factory create Gnomish Flying Machines, Human entry level
 * flying {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class GnomishFlyingMachineFactory extends CombatantFactoryBase
{
    @Inject
    public GnomishFlyingMachineFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GnomishFlyingMachineFactory(AssetManager manager) {
        super(manager, GnomishFlyingMachine);
    }

    @Override
    public Combatant get(Identifier type) {
        RangedCombatant result = builder.newRangedCombatant();
        result.setAttackSpeed(0);
        result.setArmour(0);
        result.setPiercingDamage(0);
        result.setBasicDamage(0);
        result.setHealth(150);
        result.setHealthMaximum(150);
        result.setIdentifier(objectIdentifier("GnomishFlyingMachine", result));
        result.setMovementSpeed(8 * 17);
        result.setMovementCapability(Air);
        result.setAttackRange(tiles(1));
        result.setSight(tiles(9));
        result.setType(GnomishFlyingMachine);
        result.setProjectileType(Arrow);
        return result;
    }
}