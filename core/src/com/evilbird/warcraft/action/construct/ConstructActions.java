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
import com.evilbird.warcraft.action.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.common.resource.ResourceIdentifier;
import com.evilbird.warcraft.item.unit.UnitType;

import static com.evilbird.warcraft.item.unit.resource.ResourceType.Gold;

/**
 * Defines options of specifying construction action varieties.
 *
 * @author Blair Butterworth
 */
public enum ConstructActions implements ActionIdentifier, ResourceQuantity
{
    ConstructBarracks   (UnitType.Barracks, 20f, Gold, 100f),
    ConstructFarm       (UnitType.Farm, 20f, Gold, 100f),
    ConstructTownHall   (UnitType.TownHall, 20f, Gold, 100f),
    ConstructBarracksCancel (ConstructBarracks),
    ConstructFarmCancel     (ConstructFarm),
    ConstructTownhallCancel (ConstructTownHall);

    private UnitType type;
    private float time;
    private ResourceIdentifier resource;
    private float amount;

    ConstructActions(ConstructActions other) {
        this.type = other.type;
        this.time = other.time;
        this.resource = other.resource;
        this.amount = other.amount;
    }

    ConstructActions(UnitType type, float time, ResourceIdentifier resource, float amount) {
        this.type = type;
        this.time = time;
        this.resource = resource;
        this.amount = amount;
    }

   // @Override
    public UnitType getItemType() {
        return type;
    }

    //@Override
    public float getDuration() {
        return time;
    }

    @Override
    public ResourceIdentifier getResource() {
        return resource;
    }

    @Override
    public float getValue() {
        return amount;
    }
}
