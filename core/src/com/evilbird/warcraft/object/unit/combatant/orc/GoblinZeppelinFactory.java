/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.CombatantFactoryBase;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.capability.MovementCapability.Air;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Arrow;
import static com.evilbird.warcraft.object.unit.UnitType.GoblinZeppelin;

/**
 * Instances of this factory create Goblin Zeppelins, Orcish flying scouts.
 *
 * @author Blair Butterworth
 */
public class GoblinZeppelinFactory extends CombatantFactoryBase
{
    @Inject
    public GoblinZeppelinFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GoblinZeppelinFactory(AssetManager manager) {
        super(manager, GoblinZeppelin);
    }

    @Override
    public Combatant get(Identifier type) {
        RangedCombatant result = builder.newRangedCombatant();
        result.setAttackSpeed(1.5f);
        result.setAttackRange(tiles(4));
        result.setArmour(0);
        result.setPiercingDamage(3);
        result.setBasicDamage(9);
        result.setHealth(40);
        result.setHealthMaximum(40);
        result.setIdentifier(objectIdentifier("GoblinZeppelin", result));
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Air);
        result.setSight(tiles(5));
        result.setType(GoblinZeppelin);
        result.setProjectileType(Arrow);
        return result;
    }
}