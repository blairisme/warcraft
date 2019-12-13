/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.actions;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.data.upgrade.Upgrade;
import com.evilbird.warcraft.object.unit.UnitType;
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
    BarracksButton,
    BlacksmithButton,
    ChurchButton,
    FarmButton,
    FoundryButton,
    GnomishInventorButton,
    GryphonAviaryButton,
    LumberMillButton,
    MageTowerButton,
    OilPlatformButton,
    RefineryButton,
    ScoutTowerButton,
    ShipyardButton,
    StablesButton,
    TownHallButton,

    AltarOfStormsButton,
    ForgeButton,
    EncampmentButton,
    DockyardButton,
    DragonRoostButton,
    MetalworksButton,
    GoblinAlchemistButton,
    GreatHallButton,
    OgreMoundButton,
    OilRefineryButton,
    OilRigButton,
    PigFarmButton,
    TempleOfTheDamnedButton,
    TrollLumberMillButton,
    WatchTowerButton,

    HumanWallButton,
    OrcWallButton,

    /* Building upgrade buttons */
    CannonTowerButton,
    CastleButton,
    GuardTowerButton,
    KeepButton,

    BombardTowerButton,
    FortressButton,
    LookoutTowerButton,
    StrongholdButton,

    /* Spell buttons */
    BlizzardButton,
    ExorcismButton,
    HealButton,
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
    BallistaButton,
    BattleshipButton,
    DwarvenDemolitionSquadButton,
    ElvenArcherButton,
    ElvenDestroyerButton,
    ElvenRangerButton,
    FootmanButton,
    GnomishFlyingMachineButton,
    GnomishSubmarineButton,
    GryphonRiderButton,
    KnightButton,
    MageButton,
    OilTankerButton,
    PaladinButton,
    PeasantButton,
    TransportButton,

    CatapultButton,
    DeathKnightButton,
    DragonButton,
    FerryButton,
    GiantTurtleButton,
    GoblinSappersButton,
    GoblinZeppelinButton,
    GruntButton,
    OgreButton,
    OgreJuggernaughtButton,
    OgreMageButton,
    PeonButton,
    TrollAxethrowerButton,
    TrollBerserkerButton,
    TrollDestroyerButton,
    TrollTankerButton,

    /* Upgrade buttons */
    MeleeDamage1Button,
    MeleeDamage2Button,
    MeleeDefence1Button,
    MeleeDefence2Button,
    MeleeType1Button,
    RangedAccuracy1Button,
    RangedDamage1Button,
    RangedDamage2Button,
    RangedSight1Button,
    RangedWeapon1Button,
    RangedType1Button,
    SiegeDamage1Button,
    SiegeDamage2Button,
    NavalDamage1Button,
    NavalDamage2Button,
    NavalDefence1Button,
    NavalDefence2Button,

    BlizzardUpgradeButton,
    BloodlustUpgradeButton,
    DeathAndDecayUpgradeButton,
    ExorcismUpgradeButton,
    FlameShieldUpgradeButton,
    HasteUpgradeButton,
    HealingUpgradeButton,
    InvisibilityUpgradeButton,
    PolymorphUpgradeButton,
    RaiseTheDeadUpgradeButton,
    RunesUpgradeButton,
    SlowUpgradeButton,
    UnholyArmourUpgradeButton,
    WhirlwindUpgradeButton;

    public UnitType getBuildProduct() {
        Validate.isTrue(isBuildButton() || isBuildingUpgradeButton());
        return UnitType.valueOf(getName(this, "", "Button"));
    }

    public UnitType getTrainProduct() {
        Validate.isTrue(isTrainButton());
        return UnitType.valueOf(getName(this, "", "Button"));
    }

    public Upgrade getUpgradeProduct() {
        Validate.isTrue(isUpgradeButton());
        return Upgrade.valueOf(getName(this, "", "Button"));
    }

    public boolean isBuildButton() {
        return isBetween(this, BarracksButton, WatchTowerButton);
    }

    public boolean isBuildingUpgradeButton() {
        return isBetween(this, CannonTowerButton, StrongholdButton);
    }

    public boolean isTrainButton() {
        return isBetween(this, BallistaButton, TrollTankerButton);
    }

    public boolean isUpgradeButton() {
        return isBetween(this, MeleeDamage1Button, WhirlwindUpgradeButton);
    }
}
