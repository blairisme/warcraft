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
import com.evilbird.warcraft.item.common.upgrade.Upgrade;
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
    BuildChurchButton,
    BuildFarmButton,
    BuildFoundryButton,
    BuildGnomishInventorButton,
    BuildGryphonAviaryButton,
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
    BuildDockyardButton,
    BuildDragonRoostButton,
    BuildMetalworksButton,
    BuildGoblinAlchemistButton,
    BuildGreatHallButton,
    BuildOgreMoundButton,
    BuildOilRefineryButton,
    BuildOilRigButton,
    BuildPigFarmButton,
    BuildTempleOfTheDamnedButton,
    BuildTrollLumberMillButton,
    BuildWatchTowerButton,

    BuildHumanWall,
    BuildOrcWall,

    /* Building upgrade buttons */
    BuildCannonTowerButton,
    BuildCastleButton,
    BuildGuardTowerButton,
    BuildKeepButton,

    BuildBombardTowerButton,
    BuildFortressButton,
    BuildLookoutTowerButton,
    BuildStrongholdButton,

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
    ImprovedMeleeDamageButton,
    AdvancedMeleeDamageButton,
    ImprovedMeleeDefenceButton,
    AdvancedMeleeDefenceButton,
    ImprovedMeleeTypeButton,
    ImprovedRangedAccuracyButton,
    ImprovedRangedDamageButton,
    AdvancedRangedDamageButton,
    ImprovedRangedSightButton,
    ImprovedRangedWeaponButton,
    ImprovedRangedTypeButton,
    ImprovedSiegeDamageButton,
    AdvancedSiegeDamageButton,
    ImprovedNavalDamageButton,
    AdvancedNavalDamageButton,
    ImprovedNavalDefenceButton,
    AdvancedNavalDefenceButton;

    public UnitType getBuildProduct() {
        Validate.isTrue(isBuildButton() || isBuildingUpgradeButton());
        return UnitType.valueOf(getName(this, "Build", "Button"));
    }

    public UnitType getTrainProduct() {
        Validate.isTrue(isTrainButton());
        return UnitType.valueOf(getName(this, "Train", "Button"));
    }

    public Upgrade getUpgradeProduct() {
        Validate.isTrue(isUpgradeButton());
        if (name().startsWith("Improved")) {
            return Upgrade.valueOf(getName(this, "Improved", "Button") + "1");
        }
        if (name().startsWith("Advanced")) {
            return Upgrade.valueOf(getName(this, "Advanced", "Button") + "2");
        }
        throw new UnsupportedOperationException();
    }

    public boolean isBuildButton() {
        return isBetween(this, BuildBarracksButton, BuildWatchTowerButton);
    }

    public boolean isBuildingUpgradeButton() {
        return isBetween(this, BuildCannonTowerButton, BuildStrongholdButton);
    }

    public boolean isTrainButton() {
        return isBetween(this, TrainBallistaButton, TrainTrollTankerButton);
    }

    public boolean isUpgradeButton() {
        return isBetween(this, ImprovedMeleeDamageButton, AdvancedNavalDefenceButton);
    }
}
