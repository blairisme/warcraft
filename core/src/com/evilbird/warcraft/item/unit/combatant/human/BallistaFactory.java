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
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.item.unit.combatant.CombatantBuilder;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Land;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Cannon;
import static com.evilbird.warcraft.item.unit.UnitType.Ballista;

/**
 * Instances of this factory create Ballistas, Human siege weapons.
 *
 * @author Blair Butterworth
 */
public class BallistaFactory implements AssetProvider<Item>
{
    private static final GridPoint2 ICON = new GridPoint2(46, 114);
    private static final GridPoint2 SIZE = new GridPoint2(32, 32);

    private CombatantAssets assets;
    private CombatantBuilder builder;

    @Inject
    public BallistaFactory(Device device) {
        this(device.getAssetStorage());
    }

    public BallistaFactory(AssetManager manager) {
        this.assets = new CombatantAssets(manager, Ballista, ICON, SIZE);
        this.builder = new CombatantBuilder(assets);
    }

    @Override
    public void load() {
        assets.load();
    }

    @Override
    public Item get() {
        RangedCombatant result = builder.newRangedCombatant();
        result.setAttackSpeed(3f);
        result.setDefence(0);
        result.setDamageMinimum(25);
        result.setDamageMaximum(80);
        result.setHealth(110);
        result.setHealthMaximum(110);
        result.setIdentifier(objectIdentifier("Ballista", result));
        result.setLevel(1);
        result.setName("Ballista");
        result.setMovementSpeed(8 * 5);
        result.setMovementCapability(Land);
        result.setRange(tiles(8));
        result.setSight(tiles(9));
        result.setType(Ballista);
        result.setProjectileType(Cannon);
        return result;
    }
}