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
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.CombatantFactoryTestCase;

import java.util.Map;

import static com.evilbird.warcraft.item.unit.UnitType.TrollBerserker;

/**
 * Instances of this unit test validate logic in the {@link TrollBerserkerFactory} class.
 *
 * @author Blair Butterworth
 */
public class TrollBerserkerFactoryTest extends CombatantFactoryTestCase<TrollBerserkerFactory>
{
    @Override
    protected UnitType getBuildType() {
        return TrollBerserker;
    }

    @Override
    protected TrollBerserkerFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new TrollBerserkerFactory(assets);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Maps.of("type", TrollBerserker);
    }
}
