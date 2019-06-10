/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.common.collection.Sets;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;

import java.util.Collection;
import java.util.Collections;

import static com.evilbird.warcraft.item.common.resource.ResourceQuantum.resource;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Food;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Gold;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Wood;

/**
 * Defines the resources required to create units.
 *
 * @author Blair Butterworth
 */
public class UnitCosts
{
    private UnitCosts() {
    }

    public static Collection<ResourceQuantity> costOf(Unit unit) {
        return costOf((UnitType)unit.getType());
    }

    public static Collection<ResourceQuantity> costOf(UnitType type) {
        switch (type) {
            case Farm: return Sets.of(resource(Gold, 500), resource(Wood, 250));
            case Barracks: return Sets.of(resource(Gold, 700), resource(Wood, 450));
            case TownHall: return Sets.of(resource(Gold, 1200), resource(Wood, 800));
            case Peasant: return Sets.of(resource(Gold, 400), resource(Food, 1));
            case Footman: return Sets.of(resource(Gold, 600), resource(Food, 1));
            default: return Collections.emptyList();
        }
    }

    public static Collection<ResourceQuantity> reservedResources(Item item) {
        return reservedResources(item.getType());
    }

    public static Collection<ResourceQuantity> reservedResources(Identifier type) {
        if (type instanceof UnitType) {
            switch ((UnitType)type) {
                case Peasant:
                case Footman: return Sets.of(resource(Food, 1));
            }
        }
        return Collections.emptyList();
    }
}