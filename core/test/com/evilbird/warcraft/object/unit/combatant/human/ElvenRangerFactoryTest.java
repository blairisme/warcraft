/*
 * Copyright (c) 2019, Blair Butterworth
 *  
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *  
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantFactoryTestCase;

import java.util.Map;

import static com.evilbird.warcraft.object.unit.UnitType.ElvenRanger;

/**
 * Instances of this unit test validate logic in the {@link ElvenRangerFactory} class.
 *
 * @author Blair Butterworth
 */
public class ElvenRangerFactoryTest extends CombatantFactoryTestCase<ElvenRangerFactory>
{
    @Override
    protected UnitType getBuildType() {
        return ElvenRanger;
    }

    @Override
    protected ElvenRangerFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new ElvenRangerFactory(assets);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Maps.of("type", ElvenRanger);
    }
}