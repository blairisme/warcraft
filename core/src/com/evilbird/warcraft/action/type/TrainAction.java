package com.evilbird.warcraft.action.type;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
import com.evilbird.warcraft.item.common.capability.ResourceRequirement;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.Map;

import static com.evilbird.warcraft.item.unit.UnitType.Footman;
import static com.evilbird.warcraft.item.unit.resource.ResourceType.Gold;

public enum TrainAction implements ActionIdentifier, ResourceRequirement
{
    TrainFootman (20f, Footman, Maps.of(Gold, 100f)),
    TrainPeasant (20f, Footman, Maps.of(Gold, 100f));

    private float trainTime;
    private UnitType unitType;
    private Map<ResourceIdentifier, Float> unitCost;

    private TrainAction(float trainTime, UnitType unitType, Map<ResourceIdentifier, Float> unitCost) {
        this.trainTime = trainTime;
        this.unitType = unitType;
        this.unitCost = unitCost;
    }

    public float getTrainTime() {
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
