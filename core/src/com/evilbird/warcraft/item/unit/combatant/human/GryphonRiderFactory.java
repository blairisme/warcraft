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
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Air;
import static com.evilbird.warcraft.item.unit.UnitType.GryphonRider;

/**
 * Instances of this factory create Gryphon Riders, Human heavy flying
 * {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class GryphonRiderFactory extends CombatantFactoryBase
{
    @Inject
    public GryphonRiderFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GryphonRiderFactory(AssetManager manager) {
        super(manager, GryphonRider);
    }

    @Override
    public Combatant get(Identifier type) {
        RangedCombatant result = builder.newRangedCombatant();
        result.setAttackSpeed(1.5f);
        result.setDefence(0);
        result.setDamageMinimum(8);
        result.setDamageMaximum(16);
        result.setHealth(100);
        result.setHealthMaximum(100);
        result.setIdentifier(objectIdentifier("GryphonRider", result));
        result.setLevel(1);
        result.setName("Gryphon Rider");
        result.setMovementSpeed(8 * 14);
        result.setMovementCapability(Air);
        result.setRange(tiles(4));
        result.setSight(tiles(6));
        result.setType(GryphonRider);
        //result.setProjectileType(Lightning);
        return result;
    }
}