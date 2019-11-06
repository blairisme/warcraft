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
import com.evilbird.warcraft.item.common.value.UpgradeValue;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantFactoryBase;
import com.evilbird.warcraft.item.unit.combatant.SpellCaster;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.capability.MovementCapability.Land;
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
        super(manager, Knight, Paladin);
    }

    @Override
    public Combatant get(Identifier type) {
        SpellCaster result = builder.newSpellCaster();
        result.setAttackSpeed(1f);
        result.setArmour(new UpgradeValue(MeleeDefence, 4, 6, 8));
        result.setBasicDamage(new UpgradeValue(MeleeDamage, 10, 12, 14));
        result.setPiercingDamage(2);
        result.setHealth(90f);
        result.setHealthMaximum(90f);
        result.setIdentifier(objectIdentifier("Paladin", result));
        result.setMana(200f);
        result.setManaMaximum(200f);
        result.setMovementSpeed(8 * 13);
        result.setMovementCapability(Land);
        result.setSight(tiles(4));
        result.setType(Paladin);
        return result;
    }
}