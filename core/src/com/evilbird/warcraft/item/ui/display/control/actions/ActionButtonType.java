/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.actions;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.item.unit.UnitType;
import org.apache.commons.lang3.Validate;

import static com.evilbird.engine.common.collection.EnumUtils.getName;
import static com.evilbird.engine.common.collection.EnumUtils.isBetween;

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
    BuildBlacksmithButton,
    BuildCannonTowerButton,
    BuildCastleButton,
    BuildChurchButton,
    BuildFarmButton,
    BuildFoundryButton,
    BuildGnomishInventorButton,
    BuildGryphonAviaryButton,
    BuildGuardTowerButton,
    BuildKeepButton,
    BuildLumberMillButton,
    BuildMageTowerButton,
    BuildOilPlatformButton,
    BuildRefineryButton,
    BuildScoutTowerButton,
    BuildShipyardButton,
    BuildStablesButton,
    BuildTownHallButton,

    BuildAltarOfStormsButton,
    BuildForgeButton,
    BuildEncampmentButton,
    BuildBombardTowerButton,
    BuildDockyardButton,
    BuildDragonRoostButton,
    BuildFortressButton,
    BuildMetalworksButton,
    BuildGoblinAlchemistButton,
    BuildGreatHallButton,
    BuildLookoutTowerButton,
    BuildOgreMoundButton,
    BuildOilRefineryButton,
    BuildOilRigButton,
    BuildPigFarmButton,
    BuildStrongholdButton,
    BuildTempleOfTheDamnedButton,
    BuildTrollLumberMillButton,
    BuildWatchTowerButton,

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
    TrainBallistaButton,
    TrainBattleshipButton,
    TrainDwarvenDemolitionSquadButton,
    TrainElvenArcherButton,
    TrainElvenDestroyerButton,
    TrainElvenRangerButton,
    TrainFootmanButton,
    TrainGnomishFlyingMachineButton,
    TrainGnomishSubmarineButton,
    TrainGryphonRiderButton,
    TrainKnightButton,
    TrainMageButton,
    TrainOilTankerButton,
    TrainPaladinButton,
    TrainPeasantButton,
    TrainTransportButton,

    TrainCatapultButton,
    TrainDeathKnightButton,
    TrainDragonButton,
    TrainEyeOfKilroggButton,
    TrainFerryButton,
    TrainGiantTurtleButton,
    TrainGoblinSappersButton,
    TrainGoblinZeppelinButton,
    TrainGruntButton,
    TrainOgreButton,
    TrainOgreJuggernaughtButton,
    TrainOgreMageButton,
    TrainPeonButton,
    TrainTrollAxethrowerButton,
    TrainTrollBerserkerButton,
    TrainTrollDestroyerButton,
    TrainTrollTankerButton,

    /* Upgrade buttons */
    ImprovedMeleeUpgradeButton,
    AdvancedMeleeUpgradeButton,

    ImprovedRangedUpgradeButton,
    AdvancedRangedUpgradeButton;

    public UnitType getBuildProduct() {
        Validate.isTrue(isBuildButton());
        return UnitType.valueOf(getName(this, "Build", "Button"));
    }

    public UnitType getTrainProduct() {
        Validate.isTrue(isTrainButton());
        return UnitType.valueOf(getName(this, "Train", "Button"));
    }

    public boolean isBuildButton() {
        return isBetween(this, BuildBarracksButton, BuildWatchTowerButton);
    }

    public boolean isTrainButton() {
        return isBetween(this, TrainBallistaButton, TrainTrollTankerButton);
    }
}
