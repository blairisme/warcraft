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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.common.upgrade.UpgradableValue;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Land;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.MeleeDamage;
import static com.evilbird.warcraft.item.unit.UnitType.Grunt;

/**
 * Instances of this factory create Grunts, Orcish entry level melee
 * {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class GruntFactory extends CombatantFactoryBase
{
    @Inject
    public GruntFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GruntFactory(AssetManager manager) {
        super(manager, Grunt);
    }

    @Override
    public Combatant get(Identifier type) {
        Combatant result = builder.newMeleeCombatant();
        result.setAttackSpeed(1);
        result.setAttackRange(tiles(1));
        result.setArmour(2);
        result.setBasicDamage(new UpgradableValue(MeleeDamage, 7, 9, 11));
        result.setPiercingDamage(2);
        result.setHealth(60);
        result.setHealthMaximum(60);
        result.setIdentifier(objectIdentifier("Grunt", result));
        result.setMovementSpeed(80);
        result.setMovementCapability(Land);
        result.setSight(tiles(4));
        result.setType(Grunt);
        return result;
    }
}
