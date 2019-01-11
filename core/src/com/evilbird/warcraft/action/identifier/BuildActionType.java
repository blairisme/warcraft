/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.identifier;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
import com.evilbird.warcraft.item.common.capability.ResourceRequirement;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.Map;

import static com.evilbird.warcraft.item.unit.resource.ResourceType.Gold;

public enum BuildActionType implements ActionIdentifier, ResourceRequirement
{
    BuildBarracks   (UnitType.Barracks, 20f, Maps.of(Gold, -100f)),
    BuildFarm       (UnitType.Farm, 20f, Maps.of(Gold, -100f)),
    BuildTownHall   (UnitType.TownHall, 20f, Maps.of(Gold, -100f));

    private UnitType type;
    private float time;
    private Map<ResourceIdentifier, Float> resources;

    private BuildActionType(UnitType type, float time, Map<ResourceIdentifier, Float> resources) {
        this.type = type;
        this.time = time;
        this.resources = resources;
    }

    public UnitType getBuildType() {
        return type;
    }

    public float getBuildTime() {
        return time;
    }

    public TimeDuration getBuildDuration() {
        return new TimeDuration(time);
    }

    @Override
    public Map<ResourceIdentifier, Float> getResourceRequirements() {
        return resources;
    }
}
