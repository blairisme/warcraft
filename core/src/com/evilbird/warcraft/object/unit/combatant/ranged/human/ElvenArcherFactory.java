/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.ranged.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.data.upgrade.Upgrade;
import com.evilbird.warcraft.object.common.value.UpgradeValue;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;
import com.evilbird.warcraft.object.unit.combatant.ranged.RangedUnitFactory;

import javax.inject.Inject;
import java.util.Map;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RangedAccuracy1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RangedDamage1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RangedDamage2;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RangedWeapon1;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Land;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Arrow;
import static com.evilbird.warcraft.object.unit.UnitType.ElvenArcher;

/**
 * Instances of this factory create Elven Archers, Human entry level ranged
 * {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class ElvenArcherFactory extends RangedUnitFactory
{
    private static final Map<Upgrade, Float> DAMAGE_UPGRADES = Maps.of(
        RangedDamage1, 2f, RangedDamage2, 2f,
        RangedAccuracy1, 3f, RangedWeapon1, 1f);

    @Inject
    public ElvenArcherFactory(Device device) {
        this(device.getAssetStorage());
    }

    public ElvenArcherFactory(AssetManager manager) {
        super(manager, ElvenArcher);
    }

    @Override
    public RangedCombatant get(Identifier type) {
        RangedCombatant result = builder.build();
        result.setAttackSpeed(1.5f);
        result.setAttackRange(tiles(4));
        result.setArmour(0);
        result.setBasicDamage(new UpgradeValue(6, DAMAGE_UPGRADES));
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
