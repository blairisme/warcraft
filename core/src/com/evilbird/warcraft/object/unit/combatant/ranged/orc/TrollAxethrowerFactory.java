/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.ranged.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.common.value.UpgradeValue;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;
import com.evilbird.warcraft.object.unit.combatant.ranged.RangedUnitFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.RangedDamage;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Land;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Axe;
import static com.evilbird.warcraft.object.unit.UnitType.TrollAxethrower;

/**
 * Instances of this factory create Troll Axe Throwers, Orcish entry level
 * ranged {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class TrollAxethrowerFactory extends RangedUnitFactory
{
    @Inject
    public TrollAxethrowerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public TrollAxethrowerFactory(AssetManager manager) {
        super(manager, TrollAxethrower);
    }

    @Override
    public RangedCombatant get(Identifier type) {
        RangedCombatant result = builder.build();
        result.setAttackSpeed(1.5f);
        result.setArmour(0);
        result.setArmour(0);
        result.setBasicDamage(new UpgradeValue(RangedDamage, 6, 8, 10));
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
