/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.query;

import com.evilbird.engine.object.GameObject;

import java.util.Comparator;

import static com.evilbird.warcraft.item.common.query.UnitOperations.isCombatant;

public class UnitComparators
{
    private UnitComparators() {
    }

    public static Comparator<GameObject> combatantsFirst() {
        return (itemA, itemB) -> {
            if (isCombatant(itemA)) {
                return -1;
            }
            if (isCombatant(itemB)) {
                return 1;
            }
            return 0;
        };
    }
}
