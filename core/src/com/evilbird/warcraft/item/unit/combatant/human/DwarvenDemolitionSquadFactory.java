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

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Land;
import static com.evilbird.warcraft.item.unit.UnitType.DwarvenDemolitionSquad;

/**
 * Instances of this factory create Dwarven Demolition units, Human single use
 * siege {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class DwarvenDemolitionSquadFactory extends CombatantFactoryBase
{
    @Inject
    public DwarvenDemolitionSquadFactory(Device device) {
        this(device.getAssetStorage());
    }

    public DwarvenDemolitionSquadFactory(AssetManager manager) {
        super(manager, DwarvenDemolitionSquad);
    }

    @Override
    public Combatant get(Identifier type) {
        Combatant result = builder.newMeleeCombatant();
        result.setAttackSpeed(1);
        result.setAttackRange(tiles(1));
        result.setArmour(0);
        result.setPiercingDamage(1);
        result.setBasicDamage(6);
        result.setHealth(40);
        result.setHealthMaximum(40);
        result.setIdentifier(objectIdentifier("DwarvenDemolitionSquad", result));
        result.setName("Dwarven Demolition Squad");
        result.setMovementSpeed(8 * 11);
        result.setMovementCapability(Land);
        result.setSight(tiles(4));
        result.setType(DwarvenDemolitionSquad);
        return result;
    }
}