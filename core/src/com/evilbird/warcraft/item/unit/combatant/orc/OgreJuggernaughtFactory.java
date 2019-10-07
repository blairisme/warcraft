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
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantFactoryBase;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Water;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Cannonball;
import static com.evilbird.warcraft.item.projectile.ProjectileType.FlamingCannonball;
import static com.evilbird.warcraft.item.unit.UnitType.OgreJuggernaught;

/**
 * Instances of this factory create Ogre Juggernaughts, Orcish heavy armoured
 * ships.
 *
 * @author Blair Butterworth
 */
public class OgreJuggernaughtFactory extends CombatantFactoryBase
{
    @Inject
    public OgreJuggernaughtFactory(Device device) {
        this(device.getAssetStorage());
    }

    public OgreJuggernaughtFactory(AssetManager manager) {
        super(manager, OgreJuggernaught);
    }

    @Override
    public Combatant get(Identifier type) {
        RangedCombatant result = builder.newRangedCombatant();
        result.setAttackSpeed(1.5f);
        result.setArmour(10);
        result.setPiercingDamage(2);
        result.setBasicDamage(35);
        result.setHealth(100);
        result.setHealthMaximum(100);
        result.setIdentifier(objectIdentifier("OgreJuggernaught", result));
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Water);
        result.setAttackRange(tiles(4));
        result.setSight(tiles(8));
        result.setType(OgreJuggernaught);
        result.setProjectileType(FlamingCannonball);
        return result;
    }
}