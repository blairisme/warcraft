/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.ranged.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantFactoryTestCase;
import com.evilbird.warcraft.object.unit.combatant.siege.human.BallistaFactory;

import java.util.Map;

import static com.evilbird.warcraft.object.unit.UnitType.Ballista;

/**
 * Instances of this unit test validate logic in the {@link com.evilbird.warcraft.object.unit.combatant.siege.human.BallistaFactory} class.
 *
 * @author Blair Butterworth
 */
public class BallistaFactoryTest extends CombatantFactoryTestCase<BallistaFactory>
{
    @Override
    protected UnitType getBuildType() {
        return Ballista;
    }

    @Override
    protected com.evilbird.warcraft.object.unit.combatant.siege.human.BallistaFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new com.evilbird.warcraft.object.unit.combatant.siege.human.BallistaFactory(assets);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Maps.of("type", Ballista);
    }
}