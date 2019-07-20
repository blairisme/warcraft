/*
 * Copyright (c) 2019, Blair Butterworth
 *  
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *  
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.CombatantFactoryTestCase;

import java.util.Map;

import static com.evilbird.warcraft.item.unit.UnitType.GryphonRider;

/**
 * Instances of this unit test validate logic in the {@link GryphonRiderFactory} class.
 *
 * @author Blair Butterworth
 */
public class GryphonRiderFactoryTest extends CombatantFactoryTestCase<GryphonRiderFactory>
{
    @Override
    protected UnitType getBuildType() {
        return GryphonRider;
    }

    @Override
    protected GryphonRiderFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new GryphonRiderFactory(assets);
    }

    @Override
    protected Map<String, Object> getValueProperties() {
        return Maps.of("type", GryphonRider);
    }
}
