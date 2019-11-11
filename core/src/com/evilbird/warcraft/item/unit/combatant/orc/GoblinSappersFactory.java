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

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.item.common.capability.MovementCapability.Land;
import static com.evilbird.warcraft.item.unit.UnitType.GoblinSappers;

/**
 * Instances of this factory create Goblin Demolition units, Orcish single use
 * siege {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class GoblinSappersFactory extends CombatantFactoryBase
{
    @Inject
    public GoblinSappersFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GoblinSappersFactory(AssetManager manager) {
        super(manager, GoblinSappers);
    }

    @Override
    public Combatant get(Identifier type) {
        Combatant result = builder.newMeleeCombatant();
        result.setAttackSpeed(0.9f);
        result.setArmour(2);
        result.setBasicDamage(50);
        result.setPiercingDamage(50);
        result.setHealth(60);
        result.setHealthMaximum(60);
        result.setIdentifier(objectIdentifier("GoblinSappers", result));
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Land);
        result.setSight(tiles(4));
        result.setType(GoblinSappers);
        return result;
    }
}