/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
import com.evilbird.warcraft.item.common.capability.ResourceRequirement;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.Map;

import static com.evilbird.warcraft.item.unit.UnitType.Footman;
import static com.evilbird.warcraft.item.unit.UnitType.Peasant;
import static com.evilbird.warcraft.item.unit.resource.ResourceType.Gold;

/**
 * Defines options of specifying training action varieties.
 *
 * @author Blair Butterworth
 */
public enum TrainActions implements ActionIdentifier, ResourceRequirement
{
    TrainFootman (20f, Footman, Maps.<ResourceIdentifier, Float>of(Gold, 250f)),
    TrainPeasant (20f, Peasant, Maps.<ResourceIdentifier, Float>of(Gold, 100f)),
    TrainFootmanCancel(TrainFootman),
    TrainPeasantCancel(TrainPeasant);

    private TimeDuration trainTime;
    private UnitType unitType;
    private Map<ResourceIdentifier, Float> unitCost;

    TrainActions(TrainActions other) {
        this.trainTime = other.trainTime;
        this.unitType = other.unitType;
        this.unitCost = other.unitCost;
    }

    TrainActions(float trainTime, UnitType unitType, Map<ResourceIdentifier, Float> unitCost) {
        this.trainTime = new TimeDuration(trainTime);
        this.unitType = unitType;
        this.unitCost = unitCost;
    }

    public TimeDuration getTrainTime() {
        return trainTime;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    @Override
    public Map<ResourceIdentifier, Float> getResourceRequirements() {
        return unitCost;
    }
}