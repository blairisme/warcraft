/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.flying.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnit;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnitFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Air;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Fireball;
import static com.evilbird.warcraft.object.unit.UnitType.Dragon;

/**
 * Instances of this factory create Dragon, Orcish heavy flying
 * {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class DragonFactory extends FlyingUnitFactory
{
    @Inject
    public DragonFactory(Device device) {
        this(device.getAssetStorage());
    }

    public DragonFactory(AssetManager manager) {
        super(manager, Dragon);
    }

    @Override
    public FlyingUnit get(Identifier type) {
        FlyingUnit result = builder.build();
        result.setAttackSpeed(1.5f);
        result.setArmour(0);
        result.setPiercingDamage(3);
        result.setBasicDamage(9);
        result.setHealth(40);
        result.setHealthMaximum(40);
        result.setIdentifier(objectIdentifier("Dragon", result));
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Air);
        result.setAttackRange(tiles(4));
        result.setSight(tiles(5));
        result.setType(Dragon);
        result.setProjectileType(Fireball);
        return result;
    }
}