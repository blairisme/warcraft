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

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.unit.UnitType.Paladin;

/**
 * Instances of this factory create Paladins, knights with additional attack
 * damage and sight.
 *
 * @author Blair Butterworth
 */
public class PaladinFactory extends KnightFactory
{
    @Inject
    public PaladinFactory(Device device) {
        this(device.getAssetStorage());
    }

    public PaladinFactory(AssetManager manager) {
        super(manager);
    }

    @Override
    public Combatant get(Identifier type) {
        Combatant result = builder.newMeleeCombatant();
        result.setHealth(90);
        result.setHealthMaximum(90);
        result.setIdentifier(objectIdentifier("Paladin", result));
        result.setType(Paladin);
        return result;
    }
}