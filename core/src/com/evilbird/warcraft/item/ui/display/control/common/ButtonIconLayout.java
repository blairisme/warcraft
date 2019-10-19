/*
 * Copyright (c) 2019Button, Blair Butterworth
 *  
 * This work is licensed under the MIT License. To view a copy of this
 * licenseButton, visit
 *  
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.common;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.lang.Identifier;

import java.util.HashMap;
import java.util.Map;

import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.AdvancedMeleeDamageButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.AdvancedMeleeDefenceButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.AdvancedNavalDamageButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.AdvancedNavalDefenceButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.AdvancedRangedDamageButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.AttackButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BlizzardButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BloodlustButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildAdvancedButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildAltarOfStormsButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildBarracksButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildBlacksmithButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildBombardTowerButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildCannonTowerButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildCastleButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildChurchButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildDockyardButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildDragonRoostButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildEncampmentButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildFarmButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildForgeButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildFortressButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildFoundryButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildGnomishInventorButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildGoblinAlchemistButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildGreatHallButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildGryphonAviaryButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildGuardTowerButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildKeepButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildLookoutTowerButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildLumberMillButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildMageTowerButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildMetalworksButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildOgreMoundButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildOilPlatformButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildOilRefineryButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildOilRigButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildPigFarmButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildRefineryButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildScoutTowerButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildShipyardButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildSimpleButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildStablesButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildStrongholdButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildTempleOfTheDamnedButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildTownHallButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildTrollLumberMillButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildWatchTowerButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.DeathAndDecayButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.DeathCoilButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.DefendButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.DepositButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.DetonateButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.DisembarkButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ExorcismButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.EyeOfKilroggButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.FireballButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.FlameShieldButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.GatherButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.HasteButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.HealingButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.HolyVisionButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedMeleeDamageButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedMeleeDefenceButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedNavalDamageButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedNavalDefenceButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedRangedAccuracyButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedRangedDamageButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedRangedSightButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedRangedWeaponButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedSiegeDamageButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.InvisibilityButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.LightningButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.MoveButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.PatrolButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.PolymorphButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.RaiseDeadButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.RepairButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.RunesButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.SlowButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.StopButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TouchOfDarknessButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainBallistaButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainBattleshipButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainCatapultButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainDeathKnightButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainDragonButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainDwarvenDemolitionSquadButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainElvenArcherButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainElvenDestroyerButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainElvenRangerButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainFerryButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainFootmanButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainGiantTurtleButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainGnomishFlyingMachineButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainGnomishSubmarineButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainGoblinSappersButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainGoblinZeppelinButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainGruntButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainGryphonRiderButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainKnightButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainMageButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainOgreButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainOgreJuggernaughtButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainOgreMageButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainOilTankerButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainPaladinButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainPeasantButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainPeonButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainTransportButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainTrollAxethrowerButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainTrollBerserkerButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainTrollDestroyerButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainTrollTankerButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.UnholyArmourButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.WhirlwindButton;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.Unknown;


/**
 * Defines the layout of an icon file: a texture containing a set of icons in a
 * predefined order.
 *
 * @author Blair Butterworth
 */
public class ButtonIconLayout
{
    private static final GridPoint2 size = new GridPoint2(46, 38);

    private static final Map<Identifier, GridPoint2> icons = layout(
        TrainPeasantButton,                 TrainPeonButton,                    TrainFootmanButton,                 TrainGruntButton,                  TrainElvenArcherButton,
        TrainTrollAxethrowerButton,         TrainElvenRangerButton,             TrainTrollBerserkerButton,          TrainKnightButton,                 TrainOgreButton,
        TrainPaladinButton,                 TrainOgreMageButton,                TrainDwarvenDemolitionSquadButton,  TrainGoblinSappersButton,          TrainMageButton,
        TrainDeathKnightButton,             TrainBallistaButton,                TrainCatapultButton,                TrainOilTankerButton,              TrainTrollTankerButton,
        TrainTransportButton,               TrainFerryButton,                   TrainElvenDestroyerButton,          TrainTrollDestroyerButton,         TrainBattleshipButton,
        TrainOgreJuggernaughtButton,        TrainGnomishSubmarineButton,        TrainGiantTurtleButton,             TrainGnomishFlyingMachineButton,   TrainGoblinZeppelinButton,
        TrainGryphonRiderButton,            TrainDragonButton,                  Unknown,                            Unknown,                           Unknown,
        Unknown,                            Unknown,                            Unknown,                            BuildFarmButton,                   BuildPigFarmButton,
        BuildTownHallButton,                BuildGreatHallButton,               BuildBarracksButton,                BuildEncampmentButton,             BuildLumberMillButton,
        BuildTrollLumberMillButton,         BuildBlacksmithButton,              BuildForgeButton,                   BuildShipyardButton,               BuildDockyardButton,
        BuildRefineryButton,                BuildOilRefineryButton,             BuildFoundryButton,                 BuildMetalworksButton,             BuildOilPlatformButton,
        BuildOilRigButton,                  BuildStablesButton,                 BuildOgreMoundButton,               BuildGnomishInventorButton,        BuildGoblinAlchemistButton,
        BuildScoutTowerButton,              BuildWatchTowerButton,              BuildChurchButton,                  BuildAltarOfStormsButton,          BuildMageTowerButton,
        BuildTempleOfTheDamnedButton,       BuildKeepButton,                    BuildStrongholdButton,              BuildCastleButton,                 BuildFortressButton,
        BuildCastleButton,                  BuildFortressButton,                BuildGryphonAviaryButton,           BuildDragonRoostButton,            Unknown,
        BuildGuardTowerButton,              BuildCannonTowerButton,             BuildLookoutTowerButton,            BuildBombardTowerButton,           Unknown,
        Unknown,                            Unknown,                            Unknown,                            Unknown,                           Unknown,
        RepairButton,                       GatherButton,                       BuildSimpleButton,                  BuildAdvancedButton,               orc(DepositButton),
        human(DepositButton),               CancelButton,                       Unknown,                            Unknown,                           SlowButton,
        InvisibilityButton,                 HasteButton,                        RunesButton,                        UnholyArmourButton,                LightningButton,
        FlameShieldButton,                  FireballButton,                     TouchOfDarknessButton,              DeathAndDecayButton,               WhirlwindButton,
        BlizzardButton,                     HolyVisionButton,                   HealingButton,                      DeathCoilButton,                   Unknown,
        ExorcismButton,                     EyeOfKilroggButton,                 BloodlustButton,                    Unknown,                           RaiseDeadButton,
        PolymorphButton,                    human(melee(AttackButton)),         human(ImprovedMeleeDamageButton),   human(AdvancedMeleeDamageButton),  orc(melee(AttackButton)),
        orc(ImprovedMeleeDamageButton),     orc(AdvancedMeleeDamageButton),     Unknown,                            Unknown,                           human(ranged(AttackButton)),
        human(ImprovedRangedDamageButton),  human(AdvancedRangedDamageButton),  orc(ranged(AttackButton)),          orc(ImprovedRangedDamageButton),   orc(AdvancedRangedDamageButton),
        Unknown,                            Unknown,                            human(ImprovedRangedWeaponButton),  human(ImprovedRangedSightButton),  human(ImprovedRangedAccuracyButton),
        orc(ImprovedRangedWeaponButton),    orc(ImprovedRangedSightButton),     orc(ImprovedRangedAccuracyButton),  orc(siege(AttackButton)),          orc(ImprovedSiegeDamageButton),
        human(siege(AttackButton)),         human(ImprovedSiegeDamageButton),   human(DetonateButton),              orc(DetonateButton),               human(naval(AttackButton)),
        human(ImprovedNavalDamageButton),   human(AdvancedNavalDamageButton),   orc(naval(AttackButton)),           orc(ImprovedNavalDamageButton),    orc(AdvancedNavalDamageButton),
        orc(naval(StopButton)),             orc(ImprovedNavalDefenceButton),    orc(AdvancedNavalDefenceButton),    human(naval(StopButton)),          human(ImprovedNavalDefenceButton),
        human(AdvancedNavalDefenceButton),  orc(naval(MoveButton)),             human(naval(MoveButton)),           orc(naval(DepositButton)),         human(naval(DepositButton)),
        orc(naval(GatherButton)),           human(naval(GatherButton)),         human(DisembarkButton),             orc(DisembarkButton),              human(StopButton),
        human(ImprovedMeleeDefenceButton),  human(AdvancedMeleeDefenceButton),  orc(StopButton),                    orc(ImprovedMeleeDefenceButton),   orc(AdvancedMeleeDefenceButton),
        Unknown,                            Unknown,                            Unknown,                            Unknown,                           Unknown,
        Unknown,                            Unknown,                            Unknown,                            human(PatrolButton),               orc(PatrolButton),
        human(DefendButton),                orc(DefendButton),                  Unknown,                            Unknown,                           Unknown
    );

    private static Identifier orc(Identifier id) {
        return id;
    }
    
    private static Identifier human(Identifier id) {
        return id;
    }

    private static Identifier melee(Identifier id) {
        return id;
    }

    private static Identifier ranged(Identifier id) {
        return id;
    }

    private static Identifier siege(Identifier id) {
        return id;
    }

    private static Identifier naval(Identifier id) {
        return id;
    }
    
    /**
     * Returns the size of the icon with the given {@link Identifier}. Icons
     * exist for all unitsButton, upgradesButton, spells and icon types.
     *
     * @param type  an icon identifier.
     * @return      the size of icon within an icon texture.
     */
    public GridPoint2 getSize(Identifier type) {
        return size;
    }

    /**
     * Returns the icon with the given {@link Identifier}. Icons exist for all
     * unitsButton, upgradesButton, spells and icon types.
     *
     * @param type  an icon identifier.
     * @return      the location of icon within an icon texture.
     */
    public GridPoint2 getLocation(Identifier type) {
        return icons.get(type);
    }

    private static Map<Identifier, GridPoint2> layout(Identifier ... types) {
        Map<Identifier, GridPoint2> result = new HashMap<>();
        for (int index = 0; index < types.length; ++index) {
            int column = (index % 5) * size.x;
            int row = (index / 5) * size.y;
            result.put(types[index], new GridPoint2(column, row));
        }
        return result;
    }
}
