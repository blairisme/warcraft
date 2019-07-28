/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.common;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.lang.Identifier;

import java.util.HashMap;
import java.util.Map;

import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BlizzardButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BloodlustButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildAdvancedButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildHumanWall;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildOrcWall;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildSimpleButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.DeathAndDecayButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.DeathCoilButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ExorcismButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.FireballButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.FlameShieldButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.GatherButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.HasteButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.HealingButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.HolyVisionButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.InvisibilityButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.LightningButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.PolymorphButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.RaiseDeadButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.RepairButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.RunesButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.SlowButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TouchOfDarknessButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.UnholyArmourButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.WhirlwindButton;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanArmourPlating1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanArmourPlating2;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanDefend;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanDeposit;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanDetonate;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanDisembark;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanMeleeAttack;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanMeleeDamage1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanMeleeDefence1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanMeleeDefence2;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanMove;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanPatrol;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanRangedAttack;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanRangedDamage1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanRangedDamage2;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanShipAttack;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanShipDamage1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanShipDamage2;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanShipGather;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanShipMove;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanSiegeAttack;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanSiegeDamage;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanStop;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcArmourPlating1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcArmourPlating2;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcDefend;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcDeposit;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcDetonate;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcDisembark;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcMeleeAttack;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcMeleeDamage1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcMeleeDamage2;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcMeleeDefence1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcMeleeDefence2;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcMove;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcPatrol;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcRangedAttack;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcRangedDamage1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcRangedDamage2;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcShipAttack;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcShipDamage1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcShipDamage2;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcShipDeposit;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcShipGather;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcShipMove;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcSiegeAttack;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcSiegeDamage;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcStop;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.Unknown;
import static com.evilbird.warcraft.item.unit.UnitType.AltarOfStorms;
import static com.evilbird.warcraft.item.unit.UnitType.AnduinLothar;
import static com.evilbird.warcraft.item.unit.UnitType.Ballista;
import static com.evilbird.warcraft.item.unit.UnitType.Barracks;
import static com.evilbird.warcraft.item.unit.UnitType.Battleship;
import static com.evilbird.warcraft.item.unit.UnitType.Blacksmith;
import static com.evilbird.warcraft.item.unit.UnitType.BombardTower;
import static com.evilbird.warcraft.item.unit.UnitType.CannonTower;
import static com.evilbird.warcraft.item.unit.UnitType.Castle;
import static com.evilbird.warcraft.item.unit.UnitType.Catapult;
import static com.evilbird.warcraft.item.unit.UnitType.Chogall;
import static com.evilbird.warcraft.item.unit.UnitType.Church;
import static com.evilbird.warcraft.item.unit.UnitType.CircleOfPower;
import static com.evilbird.warcraft.item.unit.UnitType.Daemon;
import static com.evilbird.warcraft.item.unit.UnitType.DarkPortal;
import static com.evilbird.warcraft.item.unit.UnitType.DeathKnight;
import static com.evilbird.warcraft.item.unit.UnitType.Dockyard;
import static com.evilbird.warcraft.item.unit.UnitType.Dragon;
import static com.evilbird.warcraft.item.unit.UnitType.DragonRoost;
import static com.evilbird.warcraft.item.unit.UnitType.DwarvenDemolitionSquad;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenArcher;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenDestroyer;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenRanger;
import static com.evilbird.warcraft.item.unit.UnitType.Encampment;
import static com.evilbird.warcraft.item.unit.UnitType.EyeOfKilrogg;
import static com.evilbird.warcraft.item.unit.UnitType.Farm;
import static com.evilbird.warcraft.item.unit.UnitType.Ferry;
import static com.evilbird.warcraft.item.unit.UnitType.Footman;
import static com.evilbird.warcraft.item.unit.UnitType.Forge;
import static com.evilbird.warcraft.item.unit.UnitType.Fortress;
import static com.evilbird.warcraft.item.unit.UnitType.Foundry;
import static com.evilbird.warcraft.item.unit.UnitType.GiantTurtle;
import static com.evilbird.warcraft.item.unit.UnitType.GnomishFlyingMachine;
import static com.evilbird.warcraft.item.unit.UnitType.GnomishInventor;
import static com.evilbird.warcraft.item.unit.UnitType.GnomishSubmarine;
import static com.evilbird.warcraft.item.unit.UnitType.GoblinAlchemist;
import static com.evilbird.warcraft.item.unit.UnitType.GoblinSappers;
import static com.evilbird.warcraft.item.unit.UnitType.GoblinZeppelin;
import static com.evilbird.warcraft.item.unit.UnitType.GoldMine;
import static com.evilbird.warcraft.item.unit.UnitType.GreatHall;
import static com.evilbird.warcraft.item.unit.UnitType.Grunt;
import static com.evilbird.warcraft.item.unit.UnitType.GryphonAviary;
import static com.evilbird.warcraft.item.unit.UnitType.GryphonRider;
import static com.evilbird.warcraft.item.unit.UnitType.GuardTower;
import static com.evilbird.warcraft.item.unit.UnitType.Guldan;
import static com.evilbird.warcraft.item.unit.UnitType.Keep;
import static com.evilbird.warcraft.item.unit.UnitType.Knight;
import static com.evilbird.warcraft.item.unit.UnitType.LookoutTower;
import static com.evilbird.warcraft.item.unit.UnitType.LumberMill;
import static com.evilbird.warcraft.item.unit.UnitType.Mage;
import static com.evilbird.warcraft.item.unit.UnitType.MageTower;
import static com.evilbird.warcraft.item.unit.UnitType.Metalworks;
import static com.evilbird.warcraft.item.unit.UnitType.Ogre;
import static com.evilbird.warcraft.item.unit.UnitType.OgreJuggernaught;
import static com.evilbird.warcraft.item.unit.UnitType.OgreMage;
import static com.evilbird.warcraft.item.unit.UnitType.OgreMound;
import static com.evilbird.warcraft.item.unit.UnitType.OilPatch;
import static com.evilbird.warcraft.item.unit.UnitType.OilPlatform;
import static com.evilbird.warcraft.item.unit.UnitType.OilRefinery;
import static com.evilbird.warcraft.item.unit.UnitType.OilRig;
import static com.evilbird.warcraft.item.unit.UnitType.OilTanker;
import static com.evilbird.warcraft.item.unit.UnitType.Paladin;
import static com.evilbird.warcraft.item.unit.UnitType.Peasant;
import static com.evilbird.warcraft.item.unit.UnitType.Peon;
import static com.evilbird.warcraft.item.unit.UnitType.PigFarm;
import static com.evilbird.warcraft.item.unit.UnitType.Refinery;
import static com.evilbird.warcraft.item.unit.UnitType.Runestone;
import static com.evilbird.warcraft.item.unit.UnitType.ScoutTower;
import static com.evilbird.warcraft.item.unit.UnitType.Shipyard;
import static com.evilbird.warcraft.item.unit.UnitType.Stables;
import static com.evilbird.warcraft.item.unit.UnitType.Stronghold;
import static com.evilbird.warcraft.item.unit.UnitType.TempleOfTheDamned;
import static com.evilbird.warcraft.item.unit.UnitType.TownHall;
import static com.evilbird.warcraft.item.unit.UnitType.Transport;
import static com.evilbird.warcraft.item.unit.UnitType.TrollAxethrower;
import static com.evilbird.warcraft.item.unit.UnitType.TrollBerserker;
import static com.evilbird.warcraft.item.unit.UnitType.TrollDestroyer;
import static com.evilbird.warcraft.item.unit.UnitType.TrollLumberMill;
import static com.evilbird.warcraft.item.unit.UnitType.TrollTanker;
import static com.evilbird.warcraft.item.unit.UnitType.UtherLightbringer;
import static com.evilbird.warcraft.item.unit.UnitType.WatchTower;
import static com.evilbird.warcraft.item.unit.UnitType.Zuljin;

/**
 * Defines the layout of an icon file: a texture containing a set of icons in a
 * predefined order.
 *
 * @author Blair Butterworth
 */
public class IconLayout
{
    private static final GridPoint2 size = new GridPoint2(46, 38);

    private static final Map<Identifier, GridPoint2> icons = layout(
        Peasant,                Peon,               Footman,                Grunt,                  ElvenArcher,
        TrollAxethrower,        ElvenRanger,        TrollBerserker,         Knight,                 Ogre,
        Paladin,                OgreMage,           DwarvenDemolitionSquad, GoblinSappers,          Mage,
        DeathKnight,            Ballista,           Catapult,               OilTanker,              TrollTanker,
        Transport,              Ferry,              ElvenDestroyer,         TrollDestroyer,         Battleship,
        OgreJuggernaught,       GnomishSubmarine,   GiantTurtle,            GnomishFlyingMachine,   GoblinZeppelin,
        GryphonRider,           Dragon,             AnduinLothar,           Guldan,                 UtherLightbringer,
        Zuljin,                 Chogall,            Daemon,                 Farm,                   PigFarm,
        TownHall,               GreatHall,          Barracks,               Encampment,             LumberMill,
        TrollLumberMill,        Blacksmith,         Forge,                  Shipyard,               Dockyard,
        Refinery,               OilRefinery,        Foundry,                Metalworks,             OilPlatform,
        OilRig,                 Stables,            OgreMound,              GnomishInventor,        GoblinAlchemist,
        ScoutTower,             WatchTower,         Church,                 AltarOfStorms,          MageTower,
        TempleOfTheDamned,      Keep,               Stronghold,             Castle,                 Fortress,
        Castle,                 Fortress,           GryphonAviary,          DragonRoost,            GoldMine,
        GuardTower,             CannonTower,        LookoutTower,           BombardTower,           OilPatch,
        DarkPortal,             CircleOfPower,      Runestone,              HumanMove,              OrcMove,
        RepairButton,           GatherButton,       BuildSimpleButton,      BuildAdvancedButton,    OrcDeposit,
        HumanDeposit,           CancelButton,       BuildHumanWall,         BuildOrcWall,           SlowButton,
        InvisibilityButton,     HasteButton,        RunesButton,            UnholyArmourButton,     LightningButton,
        FlameShieldButton,      FireballButton,     TouchOfDarknessButton,  DeathAndDecayButton,    WhirlwindButton,
        BlizzardButton,         HolyVisionButton,   HealingButton,          DeathCoilButton,        Unknown,
        ExorcismButton,         EyeOfKilrogg,       BloodlustButton,        Unknown,                RaiseDeadButton,
        PolymorphButton,        HumanMeleeAttack,   HumanMeleeDamage1,      Unknown,                OrcMeleeAttack,
        OrcMeleeDamage1,        OrcMeleeDamage2,    Unknown,                Unknown,                HumanRangedAttack,
        HumanRangedDamage1,     HumanRangedDamage2, OrcRangedAttack,        OrcRangedDamage1,       OrcRangedDamage2,
        Unknown,                Unknown,            Unknown,                Unknown,                Unknown,
        Unknown,                Unknown,            Unknown,                OrcSiegeAttack,         OrcSiegeDamage,
        HumanSiegeAttack,       HumanSiegeDamage,   HumanDetonate,          OrcDetonate,            HumanShipAttack,
        HumanShipDamage1,       HumanShipDamage2,   OrcShipAttack,          OrcShipDamage1,         OrcShipDamage2,
        OrcStop,                OrcArmourPlating1,  OrcArmourPlating2,      HumanStop,              HumanArmourPlating1,
        HumanArmourPlating2,    OrcShipMove,        HumanShipMove,          OrcShipDeposit,         HumanDeposit,
        OrcShipGather,          HumanShipGather,    HumanDisembark,         OrcDisembark,           HumanStop,
        HumanMeleeDefence1,     HumanMeleeDefence2, OrcStop,                OrcMeleeDefence1,       OrcMeleeDefence2,
        Unknown,                Unknown,            Unknown,                Unknown,                Unknown,
        Unknown,                Unknown,            Unknown,                HumanPatrol,            OrcPatrol,
        HumanDefend,            OrcDefend,          Unknown,                Unknown,                Unknown
    );

    /**
     * Returns the size of the icon with the given {@link Identifier}. Icons
     * exist for all units, upgrades, spells and icon types.
     *
     * @param type  an icon identifier.
     * @return      the size of icon within an icon texture.
     */
    public GridPoint2 getSize(Identifier type) {
        return size;
    }

    /**
     * Returns the icon with the given {@link Identifier}. Icons exist for all
     * units, upgrades, spells and icon types.
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
