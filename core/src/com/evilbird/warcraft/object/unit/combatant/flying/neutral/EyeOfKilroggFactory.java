/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.flying.neutral;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnit;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnitFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.capability.MovementCapability.Air;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.EyeOfKilrogg;

/**
 * Instances of this factory create eyes of Kilrogg, neutral creatures
 * that are conjured by magic users.
 *
 * @author Blair Butterworth
 */
public class EyeOfKilroggFactory extends FlyingUnitFactory
{
    @Inject
    public EyeOfKilroggFactory(Device device) {
        this(device.getAssetStorage());
    }

    public EyeOfKilroggFactory(AssetManager manager) {
        super(manager, EyeOfKilrogg);
    }

    @Override
    public FlyingUnit get(Identifier type) {
        FlyingUnit result = builder.build();
        result.setAttackSpeed(0);
        result.setArmour(0);
        result.setPiercingDamage(0);
        result.setBasicDamage(0);
        result.setHealth(100);
        result.setHealthMaximum(100);
        result.setIdentifier(objectIdentifier("EyeOfKilrogg", result));
        result.setMovementSpeed(8 * 14);
        result.setMovementCapability(Air);
        result.setSight(tiles(6));
        result.setType(EyeOfKilrogg);
        return result;
    }
}