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
import static com.evilbird.warcraft.item.common.capability.MovementCapability.ShallowWater;
import static com.evilbird.warcraft.item.unit.UnitType.Ferry;

/**
 * Instances of this factory create Ferries, Orcish seaborne transport
 * units.
 *
 * @author Blair Butterworth
 */
public class FerryFactory extends CombatantFactoryBase
{
    @Inject
    public FerryFactory(Device device) {
        this(device.getAssetStorage());
    }

    public FerryFactory(AssetManager manager) {
        super(manager, Ferry);
    }

    @Override
    public Combatant get(Identifier type) {
        RangedCombatant result = builder.newRangedCombatant();
        result.setAttackRange(tiles(1));
        result.setAttackSpeed(0);
        result.setArmour(0);
        result.setPiercingDamage(0);
        result.setBasicDamage(0);
        result.setHealth(150);
        result.setHealthMaximum(150);
        result.setIdentifier(objectIdentifier("Ferry", result));
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(ShallowWater);
        result.setSight(tiles(8));
        result.setType(Ferry);
        return result;
    }
}