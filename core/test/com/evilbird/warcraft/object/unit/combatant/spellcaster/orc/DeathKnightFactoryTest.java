/*
 * Copyright (c) 2019, Blair Butterworth
 *  
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *  
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.spellcaster.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantFactoryTestCase;

import java.util.Map;

import static com.evilbird.warcraft.object.unit.UnitType.DeathKnight;

public class DeathKnightFactoryTest extends CombatantFactoryTestCase<DeathKnightFactory>
{
    @Override
    protected UnitType getBuildType() {
        return DeathKnight;
    }

    @Override
    protected com.evilbird.warcraft.object.unit.combatant.spellcaster.orc.DeathKnightFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new com.evilbird.warcraft.object.unit.combatant.spellcaster.orc.DeathKnightFactory(assets);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Maps.of("type", DeathKnight);
    }
}