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
import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_WIDTH;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Sea;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Cannon;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenDestroyer;

/**
 * Instances of this factory create Elven Destroyers, Human entry level ships.
 *
 * @author Blair Butterworth
 */
public class ElvenDestroyerFactory implements AssetProvider<Item>
{
    private static final GridPoint2 ICON = new GridPoint2(92, 152);
    private static final GridPoint2 SIZE = new GridPoint2(88, 88);

    private CombatantAssets assets;
    private CombatantBuilder builder;

    @Inject
    public ElvenDestroyerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public ElvenDestroyerFactory(AssetManager manager) {
        this.assets = new CombatantAssets(manager, ElvenDestroyer, ICON, SIZE);
        this.builder = new CombatantBuilder(assets);
    }

    @Override
    public void load() {
        assets.load();
    }

    @Override
    public Item get() {
        RangedCombatant result = builder.newSeaCombatant();
        result.setDefence(4);
        result.setDamageMinimum(4);
        result.setDamageMaximum(18);
        result.setHealth(60);
        result.setHealthMaximum(60);
        result.setIdentifier(objectIdentifier("ElvenDestroyer", result));
        result.setLevel(1);
        result.setName("Elven Destroyer");
        result.setMovementSpeed(80);
        result.setMovementCapability(Sea);
        result.setRange(TILE_WIDTH * 5);
        result.setSight(TILE_WIDTH * 4);
        result.setType(ElvenDestroyer);
        result.setProjectileType(Cannon);
        return result;
    }
}
