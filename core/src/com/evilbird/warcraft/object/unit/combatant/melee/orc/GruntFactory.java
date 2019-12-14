/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.melee.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.common.value.UpgradeValue;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.melee.MeleeUnitFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.MeleeDamage;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.MeleeDefence;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Land;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.Grunt;

/**
 * Instances of this factory create Grunts, Orcish entry level melee
 * {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class GruntFactory extends MeleeUnitFactory
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
        Combatant result = builder.build();
        result.setAttackSpeed(1);
        result.setArmour(new UpgradeValue(MeleeDefence, 2, 4, 6));
        result.setBasicDamage(new UpgradeValue(MeleeDamage, 7, 9, 11));
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
