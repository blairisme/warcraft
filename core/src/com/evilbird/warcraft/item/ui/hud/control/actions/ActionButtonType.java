/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control.actions;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Defines options for specifying action button types.
 *
 * @author Blair Butterworth
 */
public enum ActionButtonType implements Identifier
{
    /* Action buttons */
    CancelButton,
    MoveButton,
    StopButton,
    AttackButton,
    DetonateButton,
    DefendButton,
    DisembarkButton,
    PatrolButton,
    RepairButton,
    GatherButton,
    DepositButton,
    BuildSimpleButton,
    BuildAdvancedButton,
    BuildCancelButton,

    /* Construction buttons */
    BuildBarracksButton,
    BuildFarmButton,
    BuildLumberMillButton,
    BuildOilPlatformButton,
    BuildShipyardButton,
    BuildTownHallButton,

    BuildDockyardButton,
    BuildEncampmentButton,
    BuildGreatHallButton,
    BuildOilRigButton,
    BuildPigFarmButton,
    BuildTrollLumberMillButton,

    BuildHumanWall,
    BuildOrcWall,

    /* Spell buttons */
    BlizzardButton,
    ExorcismButton,
    HealingButton,
    HolyVisionButton,
    FireballButton,
    FlameShieldButton,
    InvisibilityButton,
    LightningButton,
    PolymorphButton,
    SlowButton,

    BloodlustButton,
    DeathAndDecayButton,
    DeathCoilButton,
    EyeOfKilroggButton,
    HasteButton,
    RaiseDeadButton,
    RunesButton,
    TouchOfDarknessButton,
    UnholyArmourButton,
    WhirlwindButton,

    /* Training buttons */
    TrainElvenArcherButton,
    TrainElvenDestroyerButton,
    TrainFootmanButton,
    TrainOilTankerButton,
    TrainPeasantButton,

    TrainGruntButton,
    TrainPeonButton,
    TrainTrollAxethrowerButton,
    TrainTrollDestroyerButton,
    TrainTrollTankerButton,

    /* Upgrade buttons */
    ImprovedMeleeUpgradeButton,
    AdvancedMeleeUpgradeButton,

    ImprovedRangedUpgradeButton,
    AdvancedRangedUpgradeButton,
}
