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
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.unit.UnitType;

import static com.evilbird.warcraft.item.common.resource.ResourceType.Gold;

/**
 * Defines options of specifying construction action varieties.
 *
 * @author Blair Butterworth
 */
public enum ConstructActions implements ActionIdentifier, ResourceQuantity
{
    ConstructBarracks(UnitType.Barracks, 20f, Gold, 100f),  //700g, 450w, 90sec
    ConstructFarm(UnitType.Farm, 20f, Gold, 100f),          //500g, 250w, 30sec
    ConstructTownHall(UnitType.TownHall, 20f, Gold, 100f),  //1200g, 800w, 90sec
    ConstructBarracksCancel(ConstructBarracks),
    ConstructFarmCancel(ConstructFarm),
    ConstructTownhallCancel(ConstructTownHall);

    private UnitType type;
    private float time;
    private ResourceType resource;
    private float amount;

    ConstructActions(ConstructActions other) {
        this.type = other.type;
        this.time = other.time;
        this.resource = other.resource;
        this.amount = other.amount;
    }

    ConstructActions(UnitType type, float time, ResourceType resource, float amount) {
        this.type = type;
        this.time = time;
        this.resource = resource;
        this.amount = amount;
    }

    public UnitType getItemType() {
        return type;
    }

    public float getDuration() {
        return time;
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
