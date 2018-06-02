package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.item.specialized.animated.SoundIdentifier;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public enum UnitSound implements SoundIdentifier
{
    Attack,

    Selected,
    Acknowledge,

    Construct,
    Complete,

    GatherGold,
    GatherOil,
    GatherWood,

    DepositGold,
    DepositOil,
    DepositWood;

    public static UnitSound getGatherSound(ResourceType resource)
    {
        switch (resource){
            case Gold: return GatherGold;
            case Wood: return GatherWood;
            default: throw new UnsupportedOperationException();
        }
    }

    public static UnitSound getDepositSound(ResourceType resource)
    {
        switch (resource){
            case Gold: return DepositGold;
            case Wood: return DepositWood;
            default: throw new UnsupportedOperationException();
        }
    }
}
