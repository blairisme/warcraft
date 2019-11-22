/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.naval.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.common.value.UpgradeValue;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;
import com.evilbird.warcraft.object.unit.combatant.naval.NavalUnitFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Water;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.common.upgrade.UpgradeSeries.NavalDamage;
import static com.evilbird.warcraft.object.common.upgrade.UpgradeSeries.NavalDefence;
import static com.evilbird.warcraft.object.projectile.ProjectileType.FlamingCannonball;
import static com.evilbird.warcraft.object.unit.UnitType.OgreJuggernaught;

/**
 * Instances of this factory create Ogre Juggernaughts, Orcish heavy armoured
 * ships.
 *
 * @author Blair Butterworth
 */
public class OgreJuggernaughtFactory extends NavalUnitFactory
{
    @Inject
    public OgreJuggernaughtFactory(Device device) {
        this(device.getAssetStorage());
    }

    public OgreJuggernaughtFactory(AssetManager manager) {
        super(manager, OgreJuggernaught);
    }

    @Override
    public RangedCombatant get(Identifier type) {
        RangedCombatant result = builder.build();
        result.setAttackSpeed(1.5f);
        result.setArmour(new UpgradeValue(NavalDefence, 10, 20, 30));
        result.setBasicDamage(new UpgradeValue(NavalDamage, 50, 60, 70));
        result.setPiercingDamage(2);
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