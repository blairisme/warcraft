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
import com.evilbird.engine.item.animated.AnimationIdentifier;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

@SerializedType("UnitAnimation")
public enum UnitAnimation implements AnimationIdentifier
{
    Hidden,
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

    @Deprecated //TODO: Remove
    public static UnitAnimation getGatherAnimation(ResourceType resource)
    {
        switch (resource){
            case Gold: return GatherGold;
            case Wood: return GatherWood;
            default: throw new UnsupportedOperationException();
        }
    }

    @Deprecated //TODO: Remove
    public static UnitAnimation getIdleAnimation(ResourceType resource)
    {
        switch (resource){
            case Gold: return IdleGold;
            case Wood: return IdleWood;
            default: throw new UnsupportedOperationException();
        }
    }

    @Deprecated //TODO: Remove
    public static UnitAnimation getMoveAnimation(ResourceType resource)
    {
        switch (resource){
            case Gold: return MoveGold;
            case Wood: return MoveWood;
            default: throw new UnsupportedOperationException();
        }
    }
}
