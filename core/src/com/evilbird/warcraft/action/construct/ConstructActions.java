/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.warcraft.item.common.resource.ResourceIdentifier;
import com.evilbird.warcraft.item.common.resource.ResourceRequirement;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.Map;

import static com.evilbird.warcraft.item.unit.resource.ResourceType.Gold;

/**
 * Defines options of specifying construction action varieties.
 *
 * @author Blair Butterworth
 */
public enum ConstructActions implements ActionIdentifier, ResourceRequirement
{
    ConstructBarracks   (UnitType.Barracks, 20f, Maps.<ResourceIdentifier, Float>of(Gold, 100f)),
    ConstructFarm       (UnitType.Farm, 20f, Maps.<ResourceIdentifier, Float>of(Gold, 100f)),
    ConstructTownHall   (UnitType.TownHall, 20f, Maps.<ResourceIdentifier, Float>of(Gold, 100f)),
    ConstructBarracksCancel (ConstructBarracks),
    ConstructFarmCancel     (ConstructFarm),
    ConstructTownhallCancel (ConstructTownHall);

    private UnitType type;
    private float time;
    private Map<ResourceIdentifier, Float> resources;

    ConstructActions(ConstructActions other) {
        this.type = other.type;
        this.time = other.time;
        this.resources = other.resources;
    }

    ConstructActions(UnitType type, float time, Map<ResourceIdentifier, Float> resources) {
        this.type = type;
        this.time = time;
        this.resources = resources;
    }

    public UnitType getBuildType() {
        return type;
    }

    public float getBuildDuration() {
        return time;
    }

    @Override
    public Map<ResourceIdentifier, Float> getResourceRequirements() {
        return resources;
    }
}
