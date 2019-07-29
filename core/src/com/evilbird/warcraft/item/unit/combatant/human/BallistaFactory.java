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
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Land;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Cannon;
import static com.evilbird.warcraft.item.unit.UnitType.Ballista;

/**
 * Instances of this factory create Ballistas, Human siege weapons.
 *
 * @author Blair Butterworth
 */
public class BallistaFactory extends CombatantFactoryBase
{
    @Inject
    public BallistaFactory(Device device) {
        this(device.getAssetStorage());
    }

    public BallistaFactory(AssetManager manager) {
        super(manager, Ballista);
    }

    @Override
    public Combatant get(Identifier type) {
        RangedCombatant result = builder.newRangedCombatant();
        result.setAttackSpeed(3);
        result.setAttackRange(tiles(8));
        result.setArmour(0);
        result.setPiercingDamage(25);
        result.setBasicDamage(80);
        result.setHealth(110);
        result.setHealthMaximum(110);
        result.setIdentifier(objectIdentifier("Ballista", result));
        result.setMovementSpeed(8 * 5);
        result.setMovementCapability(Land);
        result.setSight(tiles(9));
        result.setType(Ballista);
        result.setProjectileType(Cannon);
        return result;
    }
}