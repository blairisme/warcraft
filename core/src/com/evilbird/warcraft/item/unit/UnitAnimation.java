package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public enum UnitAnimation implements AnimationIdentifier
{
    Idle,
    Hidden,
    Move,

    Attack,
    Die,
    Dead,
    Decompose,

    Build,
    Construct,

    GatherGold,
    GatherOil,
    GatherWood,

    DepositGold,
    DepositOil,
    DepositWood
}
