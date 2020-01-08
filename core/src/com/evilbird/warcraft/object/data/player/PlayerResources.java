/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
