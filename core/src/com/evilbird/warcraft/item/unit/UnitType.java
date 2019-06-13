/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.engine.item.ItemType;

/**
 * Defines identifiers for items varieties.
 *
 * @author Blair Butterworth
 */
@SerializedType("Units")
public enum UnitType implements ItemType
{
    /* Building - Human */
    Barracks,
    Farm,
    LumberMill,
    TownHall,

    /* Building - Orc */
    WatchTower,

    /* Building - Neutral */
    CircleOfPower,

    /* Combatant - Human */
    Footman,
    ElvenArcher,
    ElvenDestroyer,

    /* Combatant - Orc */
    Grunt,
    TrollAxethrower,
    TrollDestroyer,

    /* Combatant - Neutral */
    Seal,

    /* Gatherer */
    Peasant,
    Peon,

    /* Resource */
    GoldMine,
    OilPatch;

    public UnitFaction getFaction() {
        switch (this) {
            case Barracks:
            case Farm:
            case LumberMill:
            case TownHall:
            case Peasant:
            case ElvenArcher:
            case ElvenDestroyer:
            case Footman: return UnitFaction.Human;
            case Peon:
            case Grunt:
            case TrollDestroyer:
            case TrollAxethrower:
            case WatchTower: return UnitFaction.Orc;
            case CircleOfPower:
            case GoldMine:
            case OilPatch:
            case Seal: return UnitFaction.Neutral;
            default: throw new UnsupportedOperationException();
        }
    }
}