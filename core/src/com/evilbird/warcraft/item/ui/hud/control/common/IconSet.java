/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control.common;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.item.data.player.PlayerUpgrade;
import com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.unit.UnitFaction;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.HashMap;
import java.util.Map;

import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.MeleeDamage1;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.MeleeDamage2;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.RangedDamage1;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.RangedDamage2;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.AdvancedMeleeAttackButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.AdvancedMeleeUpgradeButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.AdvancedRangedAttackButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.AdvancedRangedUpgradeButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BlizzardButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BloodlustButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildAdvancedButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildBarracksButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildDockyardButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildEncampmentButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildFarmButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildGreatHallButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildHumanWall;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildLumberMillButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildOilPlatformButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildOilRigButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildOrcWall;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildPigFarmButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildShipyardButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildSimpleButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildTownHallButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildTrollLumberMillButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.DeathAndDecayButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.DeathCoilButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.DepositButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.ExorcismButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.FireballButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.FlameShieldButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.GatherButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.HasteButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.HealingButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.HolyVisionButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.ImprovedMeleeAttackButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.ImprovedMeleeUpgradeButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.ImprovedRangedAttackButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.ImprovedRangedUpgradeButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.InvisibilityButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.LightningButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.MeleeAttackButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.MoveButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.PolymorphButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.RaiseDeadButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.RangedAttackButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.RepairButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.RunesButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.SlowButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.TouchOfDarknessButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.UnholyArmourButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.WhirlwindButton;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconSpecialization.human;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconSpecialization.orc;
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

public class IconSet
{
    private static final GridPoint2 size = new GridPoint2(46, 38);
    private static Map<Identifier, GridPoint2> icons = layout(
        Peasant,                Peon,                   Footman,                Grunt,                  ElvenArcher,
        TrollAxethrower,        ElvenRanger,            TrollBerserker,         Knight,                 Ogre,
        Paladin,                OgreMage,               DwarvenDemolitionSquad, GoblinSappers,          Mage,
        DeathKnight,            Ballista,               Catapult,               OilTanker,              TrollTanker,
        Transport,              Ferry,                  ElvenDestroyer,         TrollDestroyer,         Battleship,
        OgreJuggernaught,       GnomishSubmarine,       GiantTurtle,            GnomishFlyingMachine,   GoblinZeppelin,
        GryphonRider,           Dragon,                 AnduinLothar,           Guldan,                 UtherLightbringer,
        Zuljin,                 Chogall,                Daemon,                 Farm,                   PigFarm,
        TownHall,               GreatHall,              Barracks,               Encampment,             LumberMill,
        TrollLumberMill,        Blacksmith,             Forge,                  Shipyard,               Dockyard,
        Refinery,               OilRefinery,            Foundry,                Metalworks,             OilPlatform,
        OilRig,                 Stables,                OgreMound,              GnomishInventor,        GoblinAlchemist,
        ScoutTower,             WatchTower,             Church,                 AltarOfStorms,          MageTower,
        TempleOfTheDamned,      Keep,                   Stronghold,             Castle,                 Fortress,
        Castle,                 Fortress,               GryphonAviary,          DragonRoost,            GoldMine,
        GuardTower,             CannonTower,            LookoutTower,           BombardTower,           OilPatch,
        DarkPortal,             CircleOfPower,          Runestone,              human(MoveButton),      orc(MoveButton),
        RepairButton,           GatherButton,           BuildSimpleButton,      BuildAdvancedButton,    orc(DepositButton),
        human(DepositButton),   CancelButton,           BuildHumanWall,         BuildOrcWall,           SlowButton,
        InvisibilityButton,     HasteButton,            RunesButton,            UnholyArmourButton,     LightningButton,
        FlameShieldButton,      FireballButton,         TouchOfDarknessButton,  DeathAndDecayButton,    WhirlwindButton,
        BlizzardButton,         HolyVisionButton,       HealingButton,          DeathCoilButton,        null,
        ExorcismButton,         EyeOfKilrogg,           BloodlustButton,        null,                   RaiseDeadButton,
        PolymorphButton,        human(MeleeAttackButton),human(MeleeDamage1),   human(MeleeDamage2),    orc(MeleeAttackButton),
        orc(MeleeDamage1),      orc(MeleeDamage2),      null,                   null,                   human(RangedAttackButton),
        human(RangedDamage1),   human(RangedDamage2),   orc(RangedAttackButton),orc(RangedDamage1),     orc(RangedDamage2)
    );
    private static Map<Identifier, Identifier> aliases = Maps.of(
        ImprovedMeleeAttackButton,      MeleeDamage1,
        ImprovedMeleeUpgradeButton,     MeleeDamage1,
        AdvancedMeleeAttackButton,      MeleeDamage2,
        AdvancedMeleeUpgradeButton,     MeleeDamage2,

        ImprovedRangedAttackButton,     RangedDamage1,
        ImprovedRangedUpgradeButton,    RangedDamage1,
        AdvancedRangedAttackButton,     RangedDamage2,
        AdvancedRangedUpgradeButton,    RangedDamage2,

        BuildBarracksButton,        Barracks,
        BuildFarmButton,            Farm,
        BuildLumberMillButton,      LumberMill,
        BuildOilPlatformButton,     OilPlatform,
        BuildShipyardButton,        Shipyard,
        BuildTownHallButton,        TownHall,

        BuildDockyardButton,        Dockyard,
        BuildEncampmentButton,      Encampment,
        BuildGreatHallButton,       GreatHall,
        BuildOilRigButton,          OilRig,
        BuildPigFarmButton,         PigFarm,
        BuildTrollLumberMillButton, TrollLumberMill
    );

    private Texture texture;

    public IconSet(Texture texture) {
        this.texture = texture;
    }

    public Drawable get(ActionButtonType button, UnitFaction faction) {
        Identifier identifier = aliases.getOrDefault(button, button);
        IconSpecialization special = new IconSpecialization(identifier, faction);
        GridPoint2 location = icons.containsKey(special) ? icons.get(special) : icons.get(identifier);

        if (location != null) {
            return TextureUtils.getDrawable(texture, location, size);
        }
        return null;
    }

    public Drawable get(UnitType type) {
        GridPoint2 location = icons.get(type);
        return TextureUtils.getDrawable(texture, location, size);
    }

    public Drawable get(PlayerUpgrade upgrade) {
        GridPoint2 location = icons.get(upgrade);
        return TextureUtils.getDrawable(texture, location, size);
    }

    private static Map<Identifier, GridPoint2> layout(Identifier ... types) {
        Map<Identifier, GridPoint2> result = new HashMap<>();
        for (int index = 0; index < types.length; ++index) {
            Identifier type = types[index];
            if (type != null) {
                int column = (index % 5) * size.x;
                int row = (index / 5) * size.y;
                GridPoint2 location = new GridPoint2(column, row);
                result.put(type, location);
            }
        }
        return result;
    }
}