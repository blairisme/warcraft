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
import com.evilbird.warcraft.item.common.value.UpgradeValue;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.common.capability.MovementCapability.Land;
import static com.evilbird.warcraft.item.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.MeleeDamage;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.MeleeDefence;
import static com.evilbird.warcraft.item.unit.UnitType.Ogre;

/**
 * Instances of this factory create Ogres, Orcish heavy assault melee
 * {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class OgreFactory extends CombatantFactoryBase
{
    @Inject
    public OgreFactory(Device device) {
        this(device.getAssetStorage());
    }

    public OgreFactory(AssetManager manager) {
        super(manager, Ogre);
    }

    @Override
    public Combatant get(Identifier type) {
        Combatant result = builder.newMeleeCombatant();
        result.setAttackSpeed(1);
        result.setArmour(new UpgradeValue(MeleeDefence, 4, 6, 8));
        result.setBasicDamage(new UpgradeValue(MeleeDamage, 12, 14, 16));
        result.setPiercingDamage(2);
        result.setHealth(90);
        result.setHealthMaximum(90);
        result.setIdentifier(objectIdentifier("Ogre", result));
        result.setMovementSpeed(8 * 13);
        result.setMovementCapability(Land);
        result.setSight(tiles(4));
        result.setType(Ogre);
        return result;
    }
}