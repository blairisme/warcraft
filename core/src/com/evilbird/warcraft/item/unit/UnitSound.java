/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.item.specialized.animated.SoundIdentifier;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

public enum UnitSound implements SoundIdentifier
{
    Attack,
    Die,

    Selected,
    Acknowledge,

    Construct,
    Complete,
    Ready,

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
