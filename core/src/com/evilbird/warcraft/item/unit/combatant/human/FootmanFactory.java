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

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.capability.MovementCapability.Land;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.MeleeDamage;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.MeleeDefence;
import static com.evilbird.warcraft.item.unit.UnitType.Footman;

/**
 * <p>
 * Instances of this factory create footman, Human entry level melee
 * {@link Combatant Combatants}.
 *</p>
 * <p>
 * Footmen are the initial line of defense against the Horde. Arrayed in
 * hardened steel armor, they courageously wield broadsword and shield in
 * hand-to-hand combat against their vile Orcish foes. The valorous Footmen
 * are ever prepared to heed the call to arms.
 * </p>
 *
 * @author Blair Butterworth
 */
public class FootmanFactory extends CombatantFactoryBase
{
    @Inject
    public FootmanFactory(Device device) {
        this(device.getAssetStorage());
    }

    public FootmanFactory(AssetManager manager) {
        super(manager, Footman);
    }

    @Override
    public Combatant get(Identifier type) {
        Combatant result = builder.newMeleeCombatant();
        result.setAttackSpeed(1);
        result.setArmour(new UpgradeValue(MeleeDefence, 2, 4, 6));
        result.setBasicDamage(new UpgradeValue(MeleeDamage, 7, 9, 11));
        result.setPiercingDamage(2);
        result.setHealth(60);
        result.setHealthMaximum(60);
        result.setIdentifier(objectIdentifier("Footman", result));
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Land);
        result.setSight(tiles(4));
        result.setType(Footman);
        return result;
    }
}
