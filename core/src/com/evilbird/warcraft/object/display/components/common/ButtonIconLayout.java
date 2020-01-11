/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.common;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.IdentifierPair;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.unit.UnitAttack;

import java.util.HashMap;
import java.util.Map;

import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.AltarOfStormsButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.AttackButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BallistaButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BarracksButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BattleshipButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BlacksmithButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BlizzardButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BlizzardUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BloodlustButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BloodlustUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BombardTowerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BuildAdvancedButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BuildSimpleButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.CannonTowerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.CastleButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.CatapultButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.ChurchButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DeathAndDecayButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DeathAndDecayUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DeathCoilButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DeathKnightButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DefendButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DepositButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DetonateButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DisembarkButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DockyardButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DragonButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DragonRoostButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DwarvenDemolitionSquadButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.ElvenArcherButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.ElvenDestroyerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.ElvenRangerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.EncampmentButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.ExorcismButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.ExorcismUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.EyeOfKilroggButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.FarmButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.FerryButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.FireballButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.FlameShieldButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.FlameShieldUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.FootmanButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.ForgeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.FortressButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.FoundryButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GatherButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GiantTurtleButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GnomishFlyingMachineButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GnomishInventorButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GnomishSubmarineButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GoblinAlchemistButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GoblinSappersButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GoblinZeppelinButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GreatHallButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GruntButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GryphonAviaryButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GryphonRiderButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GuardTowerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.HasteButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.HasteUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.HealButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.HealingUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.HolyVisionButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.HumanWallButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.InvisibilityButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.InvisibilityUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.KeepButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.KnightButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.LightningButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.LookoutTowerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.LumberMillButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MageButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MageTowerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MeleeDamage1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MeleeDamage2Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MeleeDefence1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MeleeDefence2Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MeleeType1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MetalworksButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MoveButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.NavalDamage1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.NavalDamage2Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.NavalDefence1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.NavalDefence2Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.OgreButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.OgreJuggernaughtButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.OgreMageButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.OgreMoundButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.OilPlatformButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.OilRefineryButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.OilRigButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.OilTankerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.OrcWallButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.PaladinButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.PatrolButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.PeasantButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.PeonButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.PigFarmButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.PolymorphButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.PolymorphUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RaiseDeadButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RaiseTheDeadUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RangedAccuracy1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RangedDamage1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RangedDamage2Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RangedSight1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RangedType1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RangedWeapon1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RefineryButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RepairButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RunesButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RunesUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.ScoutTowerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.ShipyardButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.SiegeDamage1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.SiegeDamage2Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.SlowButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.SlowUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.StablesButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.StopButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.StrongholdButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.TempleOfTheDamnedButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.TouchOfDarknessButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.TownHallButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.TransportButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.TrollAxethrowerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.TrollBerserkerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.TrollDestroyerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.TrollLumberMillButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.TrollTankerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.UnholyArmourButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.UnholyArmourUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.WatchTowerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.WhirlwindButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.WhirlwindUpgradeButton;
import static com.evilbird.warcraft.object.display.components.common.IconType.Unknown;

/**
 * Defines the layout of an icon file: a texture containing a set of icons in a
 * predefined order.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("checkstyle:LineLength")
public class ButtonIconLayout
{
    private final GridPoint2 size;
    private final Map<Identifier, GridPoint2> locations;
    private final Map<Identifier, Identifier> aliases;

    public ButtonIconLayout() {
        size = new GridPoint2(46, 38);
        locations = layout(
            PeasantButton,              PeonButton,                 FootmanButton,              GruntButton,                ElvenArcherButton,
            TrollAxethrowerButton,      ElvenRangerButton,          TrollBerserkerButton,       KnightButton,               OgreButton,
            PaladinButton,              OgreMageButton,             DwarvenDemolitionSquadButton, GoblinSappersButton,       MageButton,
            DeathKnightButton,          BallistaButton,             CatapultButton,             OilTankerButton,            TrollTankerButton,
            TransportButton,            FerryButton,                ElvenDestroyerButton,       TrollDestroyerButton,       BattleshipButton,
            OgreJuggernaughtButton,     GnomishSubmarineButton,     GiantTurtleButton,          GnomishFlyingMachineButton, GoblinZeppelinButton,
            GryphonRiderButton,         DragonButton,               Unknown,                    Unknown,                    Unknown,
            Unknown,                    Unknown,                    Unknown,                    FarmButton,                 PigFarmButton,
            TownHallButton,             GreatHallButton,            BarracksButton,             EncampmentButton,           LumberMillButton,
            TrollLumberMillButton,      BlacksmithButton,           ForgeButton,                ShipyardButton,             DockyardButton,
            RefineryButton,             OilRefineryButton,          FoundryButton,              MetalworksButton,           OilPlatformButton,
            OilRigButton,               StablesButton,              OgreMoundButton,            GnomishInventorButton,      GoblinAlchemistButton,
            ScoutTowerButton,           WatchTowerButton,           ChurchButton,               AltarOfStormsButton,        MageTowerButton,
            TempleOfTheDamnedButton,    KeepButton,                 StrongholdButton,           CastleButton,               FortressButton,
            CastleButton,               FortressButton,             GryphonAviaryButton,        DragonRoostButton,          Unknown,
            GuardTowerButton,           CannonTowerButton,          LookoutTowerButton,         BombardTowerButton,         Unknown,
            Unknown,                    Unknown,                    Unknown,                    human(MoveButton),          orc(MoveButton),
            RepairButton,               GatherButton,               BuildSimpleButton,          BuildAdvancedButton,        orc(DepositButton),
            human(DepositButton),       CancelButton,               HumanWallButton,            OrcWallButton,              SlowButton,
            InvisibilityButton,         HasteButton,                RunesButton,                UnholyArmourButton,         LightningButton,
            FlameShieldButton,          FireballButton,             TouchOfDarknessButton,      DeathAndDecayButton,        WhirlwindButton,
            BlizzardButton,             HolyVisionButton,           HealButton,                 DeathCoilButton,            Unknown,
            ExorcismButton,             EyeOfKilroggButton,         BloodlustButton,            Unknown,                    RaiseDeadButton,
            PolymorphButton,            human(AttackButton),        human(MeleeDamage1Button),  human(MeleeDamage2Button),  orc(AttackButton),
            orc(MeleeDamage1Button),    orc(MeleeDamage2Button),    Unknown,                    Unknown,                    human(ranged(AttackButton)),
            human(RangedDamage1Button), human(RangedDamage2Button), orc(ranged(AttackButton)),  orc(RangedDamage1Button),   orc(RangedDamage2Button),
            Unknown,                    Unknown,                    human(RangedWeapon1Button), human(RangedSight1Button),  human(RangedAccuracy1Button),
            orc(RangedWeapon1Button),   orc(RangedSight1Button),    orc(RangedAccuracy1Button), orc(SiegeDamage1Button),    orc(SiegeDamage2Button),
            human(SiegeDamage1Button),  human(SiegeDamage2Button),  human(DetonateButton),      orc(DetonateButton),        human(naval(AttackButton)),
            human(NavalDamage1Button),  human(NavalDamage2Button),  orc(naval(AttackButton)),   orc(NavalDamage1Button),    orc(NavalDamage2Button),
            orc(naval(StopButton)),     orc(NavalDefence1Button),   orc(NavalDefence2Button),   human(naval(StopButton)),   human(NavalDefence1Button),
            human(NavalDefence2Button), orc(naval(MoveButton)),     human(naval(MoveButton)),   orc(naval(DepositButton)),  human(naval(DepositButton)),
            orc(naval(GatherButton)),   human(naval(GatherButton)), human(DisembarkButton),     orc(DisembarkButton),       human(StopButton),
            human(MeleeDefence1Button), human(MeleeDefence2Button), orc(StopButton),            orc(MeleeDefence1Button),   orc(MeleeDefence2Button),
            Unknown,                    Unknown,                    Unknown,                    Unknown,                    Unknown,
            Unknown,                    Unknown,                    Unknown,                    human(PatrolButton),        orc(PatrolButton),
            human(DefendButton),        orc(DefendButton),          Unknown,                    Unknown,                    Unknown
        );
        aliases = Maps.of(
            BuildCancelButton,          CancelButton,
            BlizzardUpgradeButton,      BlizzardButton,
            BloodlustUpgradeButton,     BloodlustButton,
            DeathAndDecayUpgradeButton, DeathAndDecayButton,
            ExorcismUpgradeButton,      ExorcismButton,
            FlameShieldUpgradeButton,   FlameShieldButton,
            HasteUpgradeButton,         HasteButton,
            HealingUpgradeButton,       HealButton,
            InvisibilityUpgradeButton,  InvisibilityButton,
            PolymorphUpgradeButton,     PolymorphButton,
            RaiseTheDeadUpgradeButton,  RaiseDeadButton,
            RunesUpgradeButton,         RunesButton,
            SlowUpgradeButton,          SlowButton,
            UnholyArmourUpgradeButton,  UnholyArmourButton,
            WhirlwindUpgradeButton,     WhirlwindButton,
            human(MeleeType1Button),    PaladinButton,
            human(RangedType1Button),   ElvenRangerButton,
            orc(MeleeType1Button),      OgreMageButton,
            orc(RangedType1Button),     TrollBerserkerButton);
    }

    /**
     * Returns the size of the icon with the given {@link ActionButtonType}
     * within an icon texture file.
     */
    public GridPoint2 getSize(ActionButtonType type) {
        return size;
    }

    /**
     * Returns the size of the icon with the given {@link ActionButtonType}
     * within an icon texture file. If an icon specialization exists for the
     * given faction and attack type then this will be returned.
     */
    public GridPoint2 getSize(ActionButtonType type, WarcraftFaction faction, UnitAttack attack) {
        return size;
    }

    /**
     * Returns the location of the icon with the given {@link ActionButtonType}
     * within an icon texture file.
     */
    public GridPoint2 getLocation(ActionButtonType type) {
        return locations.get(type);
    }

    /**
     * Returns the location of the icon with the given {@link ActionButtonType}
     * within an icon texture file. If an icon specialization exists for the
     * given faction and attack type then this will be returned.
     */
    public GridPoint2 getLocation(ActionButtonType button, WarcraftFaction faction, UnitAttack attack) {
        GridPoint2 combinedLocation = getLocationImpl(combination(faction, combination(attack, button)));
        if (combinedLocation != null) {
            return combinedLocation;
        }
        GridPoint2 attackLocation = getLocationImpl(combination(attack, button));
        if (attackLocation != null) {
            return attackLocation;
        }
        GridPoint2 factionLocation = getLocationImpl(combination(faction, button));
        if (factionLocation != null) {
            return factionLocation;
        }
        return getLocationImpl(button);
    }

    private GridPoint2 getLocationImpl(Identifier identifier) {
        if (aliases.containsKey(identifier)) {
            identifier = aliases.get(identifier);
        }
        return locations.get(identifier);
    }

    private Map<Identifier, GridPoint2> layout(Identifier ... types) {
        Map<Identifier, GridPoint2> result = new HashMap<>();
        for (int index = 0; index < types.length; ++index) {
            int column = (index % 5) * size.x;
            int row = (index / 5) * size.y;
            result.put(types[index], new GridPoint2(column, row));
        }
        return result;
    }

    private Identifier human(Identifier id) {
        return combination(WarcraftFaction.Human, id);
    }

    private Identifier orc(Identifier id) {
        return combination(WarcraftFaction.Orc, id);
    }

    private Identifier ranged(Identifier id) {
        return combination(UnitAttack.Ranged, id);
    }

    private Identifier naval(Identifier id) {
        return combination(UnitAttack.Naval, id);
    }

    private Identifier combination(UnitAttack attack, Identifier id) {
        return new IdentifierPair(attack, id);
    }

    private Identifier combination(WarcraftFaction faction, Identifier id) {
        return new IdentifierPair(faction, id);
    }
}
