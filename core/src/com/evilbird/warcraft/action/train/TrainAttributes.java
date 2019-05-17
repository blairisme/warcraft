/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.common.collection.Sets;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;

import java.util.Set;

import static com.evilbird.warcraft.item.common.resource.ResourceQuantum.resource;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Food;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Gold;

public class TrainAttributes
{
    public static Set<ResourceQuantity> productionCost(TrainActions type) {
        switch (type) {
            case TrainPeasant:
            case TrainPeasantCancel: return Sets.of(resource(Gold, 400), resource(Food, 1));
            case TrainFootman:
            case TrainFootmanCancel: return Sets.of(resource(Gold, 600), resource(Food, 1));
            default: throw new UnsupportedOperationException();
        }
    }

    public static float productionTime(TrainActions type) {
        switch (type) {
            case TrainPeasant:
            case TrainFootman: return 20;
            default: throw new UnsupportedOperationException();
        }
    }
}
