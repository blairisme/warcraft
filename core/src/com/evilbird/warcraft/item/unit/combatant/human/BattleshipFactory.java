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
import static com.evilbird.warcraft.item.projectile.ProjectileType.Cannon;
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
        RangedCombatant result = builder.newSeaCombatant();
        result.setAttackSpeed(1.5f);
        result.setDefence(0);
        result.setDamageMinimum(10);
        result.setDamageMaximum(50);
        result.setHealth(60);
        result.setHealthMaximum(60);
        result.setIdentifier(objectIdentifier("Battleship", result));
        result.setLevel(1);
        result.setName("Battleship");
        result.setMovementSpeed(8 * 7);
        result.setMovementCapability(Water);
        result.setRange(tiles(7));
        result.setSight(tiles(5));
        result.setType(Battleship);
        result.setProjectileType(Cannon);
        return result;
    }
}