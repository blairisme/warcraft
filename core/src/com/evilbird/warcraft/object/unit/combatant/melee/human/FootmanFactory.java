/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.melee.human;

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
import static com.evilbird.warcraft.object.unit.UnitType.Footman;

/**
 * <p>
 * Instances of this factory create footman, Human entry level melee
 * {@link Combatant Combatants}.
 *</p>
 * <p>
 * Footmen are the initial line of defense against the Horde. Arrayed in
 * hardened steel armor, they courageously wield broadsword and shield in
 * hand-to-hand combat against their vile Orcish foes. The valorous Footmen
 * are ever prepared to heed the call to arms.
 * </p>
 *
 * @author Blair Butterworth
 */
public class FootmanFactory extends MeleeUnitFactory
{
    @Inject
    public FootmanFactory(Device device) {
        this(device.getAssetStorage());
    }

    public FootmanFactory(AssetManager manager) {
        super(manager, Footman);
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
        result.setIdentifier(objectIdentifier("Footman", result));
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Land);
        result.setSight(tiles(4));
        result.setType(Footman);
        return result;
    }
}
