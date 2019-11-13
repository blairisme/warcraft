/*
 * Copyright (c) 2019, Blair Butterworth
 *  
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *  
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.building.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.BuildingFactoryTestCase;

import java.util.Map;

import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitType.Dockyard;

/**
 * Instances of this unit test validate logic in the {@link DockyardFactory} class.
 *
 * @author Blair Butterworth
 */
public class DockyardFactoryTest extends BuildingFactoryTestCase<DockyardFactory>
{
    @Override
    protected UnitType getBuildType() {
        return Dockyard;
    }

    @Override
    protected DockyardFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new DockyardFactory(assets);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Maps.of(
                "Animation", Idle,
                "Armour", 20,
                "Health", 1100.0f,
                "HealthMaximum", 1100.0f,
                "selectable", true,
                "selected", false,
                "sight", tiles(3),
                "type", Dockyard);
    }
}