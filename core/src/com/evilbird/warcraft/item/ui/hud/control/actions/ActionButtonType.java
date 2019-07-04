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
    /* Unit action buttons */

    CancelButton,
    MoveButton,
    StopButton,

    AttackButton,
    DefendButton,
    PatrolButton,

    RepairButton,
    GatherButton,

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

    BasicArrowUpgradeButton,
    BasicAxeUpgradeButton,
    AdvancedArrowUpgradeButton,
    AdvancedAxeUpgradeButton
}
