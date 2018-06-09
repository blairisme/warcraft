package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public enum UnitAnimation implements AnimationIdentifier
{
    Hidden, //todo: remove

    Idle,
    IdleBasic,
    IdleGold,
    IdleWood,

    Move,
    MoveBasic,
    MoveGold,
    MoveWood,

    Attack,
    Build,
    Construct,

    Die,
    Dead,
    Decompose,

    Gathering,
    GatherGold,
    GatherWood;

    public static UnitAnimation getGatherAnimation(ResourceType resource)
    {
        switch (resource){
            case Gold: return GatherGold;
            case Wood: return GatherWood;
            default: throw new UnsupportedOperationException();
        }
    }

    public static UnitAnimation getIdleAnimation(ResourceType resource)
    {
        switch (resource){
            case Gold: return IdleGold;
            case Wood: return IdleWood;
            default: throw new UnsupportedOperationException();
        }
    }

    public static UnitAnimation getMoveAnimation(ResourceType resource)
    {
        switch (resource){
            case Gold: return MoveGold;
            case Wood: return MoveWood;
            default: throw new UnsupportedOperationException();
        }
    }
}
