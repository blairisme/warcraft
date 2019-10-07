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
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Water;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Cannonball;
import static com.evilbird.warcraft.item.projectile.ProjectileType.FlamingCannonball;
import static com.evilbird.warcraft.item.unit.UnitType.Battleship;

/**
 * Instances of this factory create Battleships, Human heavy armoured ships.
 *
 * @author Blair Butterworth
 */
public class BattleshipFactory extends CombatantFactoryBase
{
    @Inject
    public BattleshipFactory(Device device) {
        this(device.getAssetStorage());
    }

    public BattleshipFactory(AssetManager manager) {
        super(manager, Battleship);
    }

    @Override
    public Combatant get(Identifier type) {
        RangedCombatant result = builder.newRangedCombatant();
        result.setAttackSpeed(1.5f);
        result.setAttackRange(tiles(7));
        result.setArmour(0);
        result.setPiercingDamage(10);
        result.setBasicDamage(50);
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