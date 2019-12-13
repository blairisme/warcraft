/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.data.player;

import com.evilbird.warcraft.data.resource.ResourceQuantity;

import java.util.Collection;

public class PlayerResources
{
    private PlayerResources() {
    }

    public static boolean hasResources(Player player, Collection<ResourceQuantity> resourceQuantities) {
        for (ResourceQuantity resourceQuantity: resourceQuantities) {
            if (player.getResource(resourceQuantity.getType()) < resourceQuantity.getValue()){
                return false;
            }
        }
        return true;
    }
}
