/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.CombatantFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.capability.MovementCapability.Land;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.DwarvenDemolitionSquad;

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
        result.setAttackSpeed(0.75f);
        result.setArmour(0);
        result.setBasicDamage(50);
        result.setPiercingDamage(50);
        result.setHealth(40);
        result.setHealthMaximum(40);
        result.setIdentifier(objectIdentifier("DwarvenDemolitionSquad", result));
        result.setMovementSpeed(8 * 11);
        result.setMovementCapability(Land);
        result.setSight(tiles(4));
        result.setType(DwarvenDemolitionSquad);
        return result;
    }
}