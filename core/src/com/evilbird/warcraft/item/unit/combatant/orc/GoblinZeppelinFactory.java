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
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Land;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Arrow;
import static com.evilbird.warcraft.item.unit.UnitType.GoblinZeppelin;

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
        result.setName("Goblin Zeppelin");
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Land);
        result.setSight(tiles(5));
        result.setType(GoblinZeppelin);
        result.setProjectileType(Arrow);
        return result;
    }
}