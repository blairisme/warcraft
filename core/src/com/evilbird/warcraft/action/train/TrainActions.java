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
import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.unit.UnitType;

import static com.evilbird.warcraft.item.common.resource.ResourceType.Gold;
import static com.evilbird.warcraft.item.unit.UnitType.Footman;
import static com.evilbird.warcraft.item.unit.UnitType.Peasant;

/**
 * Defines options of specifying training action varieties.
 *
 * @author Blair Butterworth
 */
public enum TrainActions implements ActionIdentifier, ResourceQuantity
{
    TrainFootman(Footman, 20f, Gold, 250f), //600g
    TrainPeasant(Peasant, 20f, Gold, 100f), //400g
    TrainFootmanCancel(TrainFootman),
    TrainPeasantCancel(TrainPeasant);

    private float trainTime;
    private UnitType unitType;
    private ResourceType resource;
    private float amount;

    TrainActions(TrainActions other) {
        this.trainTime = other.trainTime;
        this.unitType = other.unitType;
        this.resource = other.resource;
        this.amount = other.amount;
    }

    TrainActions(UnitType unitType, float trainTime, ResourceType resource, float amount) {
        this.trainTime = trainTime;
        this.unitType = unitType;
        this.resource = resource;
        this.amount = amount;
    }

    public float getDuration() {
        return trainTime;
    }

    public ItemType getItemType() {
        return unitType;
    }

    @Override
    public ResourceType getResource() {
        return resource;
    }

    @Override
    public float getValue() {
        return amount;
    }
}
