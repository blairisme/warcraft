/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.common;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Defines varieties of icon levels.
 *
 * @author Blair Butterworth
 */
public enum IconType implements Identifier
{
    Any,
    Unknown,

    HumanMove,
    HumanShipMove,

    OrcMove,
    OrcShipMove,

    HumanDeposit,
    HumanShipDeposit,

    OrcDeposit,
    OrcShipDeposit,

    HumanShipGather,
    OrcShipGather,

    HumanMeleeAttack,
    HumanMeleeDamage1,
    HumanMeleeDamage2,

    OrcMeleeAttack,
    OrcMeleeDamage1,
    OrcMeleeDamage2,

    HumanRangedAttack,
    HumanRangedDamage1,
    HumanRangedDamage2,

    OrcRangedAttack,
    OrcRangedDamage1,
    OrcRangedDamage2,

    HumanSiegeAttack,
    HumanSiegeDamage,

    OrcSiegeAttack,
    OrcSiegeDamage,

    HumanShipAttack,
    HumanShipDamage1,
    HumanShipDamage2,

    OrcShipAttack,
    OrcShipDamage1,
    OrcShipDamage2,

    HumanDetonate,
    OrcDetonate,

    HumanShipStop,
    HumanArmourPlating1,
    HumanArmourPlating2,

    HumanStop,
    HumanMeleeDefence1,
    HumanMeleeDefence2,

    OrcShipStop,
    OrcArmourPlating1,
    OrcArmourPlating2,

    OrcStop,
    OrcMeleeDefence1,
    OrcMeleeDefence2,

    HumanDisembark,
    OrcDisembark,

    HumanPatrol,
    OrcPatrol,

    HumanDefend,
    OrcDefend,
}
