/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.warcraft.data.resource.ResourceType;

import static com.evilbird.warcraft.data.resource.ResourceType.Gold;
import static com.evilbird.warcraft.data.resource.ResourceType.Oil;
import static com.evilbird.warcraft.data.resource.ResourceType.Wood;

/**
 * Defines options of specifying gather action varieties.
 *
 * @author Blair Butterworth
 */
public enum GatherActions implements ActionIdentifier
{
    GatherGold,
    GatherOil,
    GatherWood,
    GatherCancel;

    /**
     * Returns the action identifier that will obtain resources of the given
     * type.
     */
    public static GatherActions forResource(ResourceType resource) {
        switch (resource) {
            case Gold: return GatherGold;
            case Oil: return GatherOil;
            case Wood: return GatherWood;
            default: throw new UnsupportedOperationException();
        }
    }

    /**
     * Returns the type of resources obtained by actions of the given type.
     */
    public static ResourceType targetedResource(GatherActions action) {
        switch (action) {
            case GatherGold: return Gold;
            case GatherOil: return Oil;
            case GatherWood: return Wood;
            default: throw new UnsupportedOperationException();
        }
    }
}
