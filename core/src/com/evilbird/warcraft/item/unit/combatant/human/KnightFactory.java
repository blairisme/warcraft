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
import static com.evilbird.warcraft.item.common.capability.MovementCapability.Land;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.MeleeDamage;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.MeleeDefence;
import static com.evilbird.warcraft.item.unit.UnitType.Knight;

/**
 * Instances of this factory create knights, Human mounted heavy assault melee
 * {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class KnightFactory extends CombatantFactoryBase
{
    @Inject
    public KnightFactory(Device device) {
        this(device.getAssetStorage());
    }

    public KnightFactory(AssetManager manager) {
        super(manager, Knight);
    }

    @Override
    public Combatant get(Identifier type) {
        Combatant result = builder.newMeleeCombatant();
        result.setAttackSpeed(1);
        result.setArmour(new UpgradeSequence(MeleeDefence, 4, 6, 8));
        result.setBasicDamage(new UpgradeSequence(MeleeDamage, 10, 12, 14));
        result.setPiercingDamage(2);
        result.setHealth(90);
        result.setHealthMaximum(90);
        result.setIdentifier(objectIdentifier("Knight", result));
        result.setMovementSpeed(8 * 13);
        result.setMovementCapability(Land);
        result.setSight(tiles(4));
        result.setType(Knight);
        return result;
    }
}