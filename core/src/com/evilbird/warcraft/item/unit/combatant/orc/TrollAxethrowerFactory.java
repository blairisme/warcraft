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
import com.evilbird.warcraft.item.common.upgrade.UpgradeSequence;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantFactoryBase;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.capability.MovementCapability.Land;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.RangedDamage;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Axe;
import static com.evilbird.warcraft.item.unit.UnitType.TrollAxethrower;

/**
 * Instances of this factory create Troll Axe Throwers, Orcish entry level
 * ranged {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class TrollAxethrowerFactory extends CombatantFactoryBase
{
    @Inject
    public TrollAxethrowerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public TrollAxethrowerFactory(AssetManager manager) {
        super(manager, TrollAxethrower);
    }

    @Override
    public Combatant get(Identifier type) {
        RangedCombatant result = builder.newRangedCombatant();
        result.setAttackSpeed(1.5f);
        result.setArmour(0);
        result.setArmour(0);
        result.setBasicDamage(new UpgradeSequence(RangedDamage, 6, 8, 10));
        result.setPiercingDamage(3);
        result.setHealth(40);
        result.setHealthMaximum(40);
        result.setIdentifier(objectIdentifier("TrollAxethrower", result));
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Land);
        result.setAttackRange(tiles(4));
        result.setSight(tiles(5));
        result.setType(TrollAxethrower);
        result.setProjectileType(Axe);
        return result;
    }
}
