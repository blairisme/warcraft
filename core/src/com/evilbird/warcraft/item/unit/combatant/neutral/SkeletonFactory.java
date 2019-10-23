/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.neutral;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.capability.MovementCapability.Land;
import static com.evilbird.warcraft.item.unit.UnitType.Skeleton;

/**
 * Instances of this factory create daemons, neutral flying creatures that are
 * conjured by magic users.
 *
 * @author Blair Butterworth
 */
public class SkeletonFactory extends CombatantFactoryBase
{
    @Inject
    public SkeletonFactory(Device device) {
        this(device.getAssetStorage());
    }

    public SkeletonFactory(AssetManager manager) {
        super(manager, Skeleton);
    }

    @Override
    public Combatant get(Identifier type) {
        Combatant result = builder.newMeleeCombatant();
        result.setAttackSpeed(1);
        result.setArmour(2);
        result.setBasicDamage(7);
        result.setPiercingDamage(2);
        result.setHealth(60);
        result.setHealthMaximum(60);
        result.setIdentifier(objectIdentifier("Skeleton", result));
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Land);
        result.setSight(tiles(4));
        result.setType(Skeleton);
        return result;
    }
}
