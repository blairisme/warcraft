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
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.common.upgrade.Upgrade;
import com.evilbird.warcraft.item.common.upgrade.UpgradeCombination;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantFactoryBase;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;

import javax.inject.Inject;
import java.util.Map;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.capability.MovementCapability.Land;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedAccuracy1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedWeapon1;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Arrow;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenArcher;

/**
 * Instances of this factory create Elven Archers, Human entry level ranged
 * {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class ElvenArcherFactory extends CombatantFactoryBase
{
    private static final Map<Upgrade, Integer> DAMAGE_UPGRADES = Maps.of(
        RangedDamage1, 2, RangedDamage2, 2,
        RangedAccuracy1, 3, RangedWeapon1, 1);

    @Inject
    public ElvenArcherFactory(Device device) {
        this(device.getAssetStorage());
    }

    public ElvenArcherFactory(AssetManager manager) {
        super(manager, ElvenArcher);
    }

    @Override
    public Combatant get(Identifier type) {
        RangedCombatant result = builder.newRangedCombatant();
        result.setAttackSpeed(1.5f);
        result.setAttackRange(tiles(4));
        result.setArmour(0);
        result.setBasicDamage(new UpgradeCombination(6, DAMAGE_UPGRADES));
        result.setPiercingDamage(3);
        result.setHealth(40);
        result.setHealthMaximum(40);
        result.setIdentifier(objectIdentifier("ElvenArcher", result));
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Land);
        result.setSight(tiles(5));
        result.setType(ElvenArcher);
        result.setProjectileType(Arrow);
        return result;
    }
}
