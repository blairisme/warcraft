/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour;

import com.evilbird.engine.behaviour.BehaviourIdentifier;

/**
 * Defines identifiers for behaviour varieties.
 *
 * @author Blair Butterworth
 */
public enum WarcraftBehaviour implements BehaviourIdentifier
{
    Human1,
    Human2,
    Human3,
    Human4,
    Human5,
    Human6,
    Human7,
    Human8,
    Human9,
    Human10,
    Human11,
    Human12,
    Human13,
    Human14,
    HumanEasy,

    Orc1,
    Orc2,
    Orc3,
    Orc4,
    Orc5,
    Orc6,
    Orc7,
    Orc8,
    Orc9,
    Orc10,
    Orc11,
    Orc12,
    Orc13,
    Orc14,
    OrcEasy;

    public boolean isHuman() {
        return !isOrc();
    }

    public boolean isOrc() {
        return this.ordinal() >= Orc1.ordinal();
    }
}
