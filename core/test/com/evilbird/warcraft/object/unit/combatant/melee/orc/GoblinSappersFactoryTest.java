/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.melee.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantFactoryTestCase;

import java.util.Map;

import static com.evilbird.warcraft.object.unit.UnitType.GoblinSappers;

/**
 * Instances of this unit test validate logic in the {@link com.evilbird.warcraft.object.unit.combatant.melee.orc.GoblinSappersFactory} class.
 *
 * @author Blair Butterworth
 */
public class GoblinSappersFactoryTest extends CombatantFactoryTestCase<com.evilbird.warcraft.object.unit.combatant.melee.orc.GoblinSappersFactory>
{
    @Override
    protected UnitType getBuildType() {
        return GoblinSappers;
    }

    @Override
    protected com.evilbird.warcraft.object.unit.combatant.melee.orc.GoblinSappersFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new GoblinSappersFactory(assets);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Maps.of("type", GoblinSappers);
    }
}
