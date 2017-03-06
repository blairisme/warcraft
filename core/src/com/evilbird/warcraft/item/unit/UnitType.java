package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.item.ItemIdentifier;

/**
 * Instances of this class define identifiers for items
 *
 * @author Blair Butterworth
 */
public enum UnitType implements ItemIdentifier
{
    /* Human Units */
    Footman,
    Peasant,

    /* Human Buildings */
    TownHall,
    Farm,
    Barracks,
    BarracksPrototype,

    /* Orc Units */
    Grunt,

    /* Neutral Buildings */
    GoldMine,
    Tree
}