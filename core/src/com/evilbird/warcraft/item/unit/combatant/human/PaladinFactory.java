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
import com.evilbird.warcraft.item.common.upgrade.UpgradeSequence;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.state.MovementCapability.Land;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.MeleeDamage;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.MeleeDefence;
import static com.evilbird.warcraft.item.unit.UnitType.Knight;
import static com.evilbird.warcraft.item.unit.UnitType.Paladin;

/**
 * Instances of this factory create Paladins, knights with additional attack
 * damage and sight.
 *
 * @author Blair Butterworth
 */
public class PaladinFactory extends CombatantFactoryBase
{
    @Inject
    public PaladinFactory(Device device) {
        this(device.getAssetStorage());
    }

    public PaladinFactory(AssetManager manager) {
        super(manager, Knight);
    }

    @Override
    public Combatant get(Identifier type) {
        Combatant result = builder.newMeleeCombatant();
        result.setAttackSpeed(1);
        result.setArmour(new UpgradeSequence<>(MeleeDefence, 4, 6, 8));
        result.setBasicDamage(new UpgradeSequence<>(MeleeDamage, 12, 14, 16));
        result.setPiercingDamage(8);
        result.setHealth(90);
        result.setHealthMaximum(90);
        result.setIdentifier(objectIdentifier("Paladin", result));
        result.setMovementSpeed(8 * 13);
        result.setMovementCapability(Land);
        result.setSight(tiles(5));
        result.setType(Paladin);
        return result;
    }
}