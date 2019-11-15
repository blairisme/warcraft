/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.flying.neutral;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantFactoryTestCase;

import java.util.Map;

import static com.evilbird.warcraft.object.unit.UnitType.Daemon;
import static com.evilbird.warcraft.object.unit.UnitType.Dragon;

/**
 * Instances of this unit test validate logic in the {@link DaemonFactory} class.
 *
 * @author Blair Butterworth
 */
public class DaemonFactoryTest extends CombatantFactoryTestCase<DaemonFactory>
{
    @Override
    protected UnitType getBuildType() {
        return Dragon;
    }

    @Override
    protected DaemonFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new DaemonFactory(assets);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Maps.of("type", Daemon);
    }
}
