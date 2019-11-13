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

import static com.evilbird.warcraft.object.unit.UnitType.Transport;

/**
 * Instances of this unit test validate logic in the {@link TransportFactory} class.
 *
 * @author Blair Butterworth
 */
public class TransportFactoryTest extends CombatantFactoryTestCase<TransportFactory>
{
    @Override
    protected UnitType getBuildType() {
        return Transport;
    }

    @Override
    protected TransportFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new TransportFactory(assets);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Maps.of("type", Transport);
    }
}