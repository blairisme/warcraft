/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.flying.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnit;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnitFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Air;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.GnomishFlyingMachine;

/**
 * Instances of this factory create Gnomish Flying Machines, Human entry level
 * flying {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class GnomishFlyingMachineFactory extends FlyingUnitFactory
{
    @Inject
    public GnomishFlyingMachineFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GnomishFlyingMachineFactory(AssetManager manager) {
        super(manager, GnomishFlyingMachine);
    }

    @Override
    public FlyingUnit get(Identifier type) {
        FlyingUnit result = builder.build();
        result.setHealth(150);
        result.setHealthMaximum(150);
        result.setIdentifier(objectIdentifier("GnomishFlyingMachine", result));
        result.setMovementSpeed(8 * 17);
        result.setMovementCapability(Air);
        result.setSight(tiles(9));
        result.setType(GnomishFlyingMachine);
        return result;
    }
}