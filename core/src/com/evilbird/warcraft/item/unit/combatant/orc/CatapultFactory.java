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
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Land;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Cannon;
import static com.evilbird.warcraft.item.unit.UnitType.Catapult;

/**
 * Instances of this factory create Catapults, Orcish siege weapons.
 *
 * @author Blair Butterworth
 */
public class CatapultFactory implements GameFactory<Combatant>
{
    private static final GridPoint2 SIZE = new GridPoint2(32, 32);

    private CombatantAssets assets;
    private CombatantBuilder builder;

    @Inject
    public CatapultFactory(Device device) {
        this(device.getAssetStorage());
    }

    public CatapultFactory(AssetManager manager) {
        this.assets = new CombatantAssets(manager, Catapult, SIZE);
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
        result.setAttackSpeed(3);
        result.setDefence(0);
        result.setDamageMinimum(25);
        result.setDamageMaximum(80);
        result.setHealth(110);
        result.setHealthMaximum(110);
        result.setIdentifier(objectIdentifier("Catapult", result));
        result.setLevel(1);
        result.setName("Catapult");
        result.setMovementSpeed(8 * 5);
        result.setMovementCapability(Land);
        result.setRange(tiles(8));
        result.setSight(tiles(9));
        result.setType(Catapult);
        result.setProjectileType(Cannon);
        return result;
    }
}