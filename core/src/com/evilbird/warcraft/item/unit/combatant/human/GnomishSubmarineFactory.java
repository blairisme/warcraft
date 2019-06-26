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
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Water;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Cannon;
import static com.evilbird.warcraft.item.unit.UnitType.GnomishSubmarine;

/**
 * Instances of this factory create Gnomish Submarines, Human underwater
 * seaborne {@link RangedCombatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class GnomishSubmarineFactory implements AssetProvider<Item>
{
    private static final GridPoint2 ICON = new GridPoint2(46, 190);
    private static final GridPoint2 SIZE = new GridPoint2(88, 88);

    private CombatantAssets assets;
    private CombatantBuilder builder;

    @Inject
    public GnomishSubmarineFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GnomishSubmarineFactory(AssetManager manager) {
        this.assets = new CombatantAssets(manager, GnomishSubmarine, ICON, SIZE);
        this.builder = new CombatantBuilder(assets);
    }

    @Override
    public void load() {
        assets.load();
    }

    @Override
    public Item get() {
        RangedCombatant result = builder.newSeaCombatant();
        result.setAttackSpeed(1.5f);
        result.setDefence(10);
        result.setDamageMinimum(2);
        result.setDamageMaximum(35);
        result.setHealth(100);
        result.setHealthMaximum(100);
        result.setIdentifier(objectIdentifier("GnomishSubmarine", result));
        result.setLevel(1);
        result.setName("Gnomish Submarine");
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Water);
        result.setRange(tiles(4));
        result.setSight(tiles(8));
        result.setType(GnomishSubmarine);
        result.setProjectileType(Cannon);
        return result;
    }
}