/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.data.resource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.evilbird.warcraft.data.resource.ResourceType.Food;
import static com.evilbird.warcraft.data.resource.ResourceType.Gold;
import static com.evilbird.warcraft.data.resource.ResourceType.Oil;
import static com.evilbird.warcraft.data.resource.ResourceType.Wood;

/**
 * @author Blair Butterworth
 */
public class ResourceOperations
{
    private ResourceOperations() {
    }

    public static ResourceQuantity G(int gold) {
        return new ResourceQuantity(Gold, gold);
    }

    public static ResourceQuantity O(int oil) {
        return new ResourceQuantity(Oil, oil);
    }

    public static ResourceQuantity W(int wood) {
        return new ResourceQuantity(Wood, wood);
    }

    public static ResourceQuantity F(int food) {
        return new ResourceQuantity(Food, food);
    }

    public static ResourceSet Resources(ResourceQuantity ... quantities) {
        return new ResourceSet(Arrays.asList(quantities));
    }

    public static ResourceSet Resources(int gold, int wood, int oil, int food) {
        Set<ResourceQuantity> quantities = new HashSet<>();
        if (gold > 0) {
            quantities.add(new ResourceQuantity(Gold, gold));
        }
        if (wood > 0) {
            quantities.add(new ResourceQuantity(Wood, wood));
        }
        if (oil > 0) {
            quantities.add(new ResourceQuantity(Oil, oil));
        }
        if (food > 0) {
            quantities.add(new ResourceQuantity(Food, food));
        }
        return new ResourceSet(quantities);
    }
}
