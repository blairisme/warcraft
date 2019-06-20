/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.data.player;

import com.evilbird.warcraft.item.common.resource.ResourceQuantity;

import java.util.Collection;

public class PlayerResources
{
    private PlayerResources() {
    }

    public static boolean hasResources(Player player, Collection<ResourceQuantity> resourceQuantities) {
        for (ResourceQuantity resourceQuantity: resourceQuantities) {
            if (player.getResource(resourceQuantity.getResource()) < resourceQuantity.getValue()){
                return false;
            }
        }
        return true;
    }
}
