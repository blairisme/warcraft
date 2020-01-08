/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.naval.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;
import com.evilbird.warcraft.object.unit.combatant.naval.NavalUnitFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Water;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Torpedo;
import static com.evilbird.warcraft.object.unit.UnitType.GiantTurtle;

/**
 * Instances of this factory create Giant Turtles, Orcish underwater
 * seaborne {@link RangedCombatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class GiantTurtleFactory extends NavalUnitFactory
{
    @Inject
    public GiantTurtleFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GiantTurtleFactory(AssetManager manager) {
        super(manager, GiantTurtle);
    }

    @Override
    public RangedCombatant get(Identifier type) {
        RangedCombatant result = builder.build();
        result.setAttackSpeed(1.5f);
        result.setArmour(10);
        result.setPiercingDamage(2);
        result.setBasicDamage(35);
        result.setHealth(100);
        result.setHealthMaximum(100);
        result.setIdentifier(objectIdentifier("GiantTurtle", result));
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Water);
        result.setAttackRange(tiles(4));
        result.setSight(tiles(8));
        result.setType(GiantTurtle);
        result.setProjectileType(Torpedo);
        return result;
    }
}