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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.item.unit.combatant.CombatantBuilder;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Air;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Arrow;
import static com.evilbird.warcraft.item.unit.UnitType.GnomishFlyingMachine;

/**
 * Instances of this factory create Gnomish Flying Machines, Human entry level
 * flying {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class GnomishFlyingMachineFactory implements GameFactory<Combatant>
{
    private static final GridPoint2 SIZE = new GridPoint2(32, 32);

    private CombatantAssets assets;
    private CombatantBuilder builder;

    @Inject
    public GnomishFlyingMachineFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GnomishFlyingMachineFactory(AssetManager manager) {
        this.assets = new CombatantAssets(manager, GnomishFlyingMachine, SIZE);
        this.builder = new CombatantBuilder(assets);
    }

    @Override
    public void load(Identifier context) {
        assets.load();
    }

    @Override
    public void unload(Identifier context) {
    }

    @Override
    public Combatant get(Identifier type) {
        RangedCombatant result = builder.newRangedCombatant();
        result.setAttackSpeed(0);
        result.setDefence(0);
        result.setDamageMinimum(0);
        result.setDamageMaximum(0);
        result.setHealth(150);
        result.setHealthMaximum(150);
        result.setIdentifier(objectIdentifier("GnomishFlyingMachine", result));
        result.setLevel(1);
        result.setName("Gnomish Flying Machine");
        result.setMovementSpeed(8 * 17);
        result.setMovementCapability(Air);
        result.setRange(tiles(1));
        result.setSight(tiles(9));
        result.setType(GnomishFlyingMachine);
        result.setProjectileType(Arrow);
        return result;
    }
}