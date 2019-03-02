/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.engine.item.ItemType;

/**
 * Instances of this class define identifiers for items.
 *
 * @author Blair Butterworth
 */
@SerializedType("Units")
public enum UnitType implements ItemType
{
    /* Building */
    TownHall,
    Farm,
    Barracks,

    /* Combatant */
    Footman,
    Peasant,
    Grunt,

    /* Resource */
    GoldMine,
    Tree,
    OilPatch
}