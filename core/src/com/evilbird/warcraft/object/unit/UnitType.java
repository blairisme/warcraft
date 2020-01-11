/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit;

import com.evilbird.engine.object.GameObjectType;
import com.evilbird.warcraft.common.WarcraftFaction;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Neutral;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;
import static com.evilbird.warcraft.object.unit.UnitArchetype.Archer;
import static com.evilbird.warcraft.object.unit.UnitArchetype.Cavalry;
import static com.evilbird.warcraft.object.unit.UnitArchetype.CavalryRequisite;
import static com.evilbird.warcraft.object.unit.UnitArchetype.CavalryUpgrader;
import static com.evilbird.warcraft.object.unit.UnitArchetype.CombatantProducer;
import static com.evilbird.warcraft.object.unit.UnitArchetype.CombatantUpgrader;
import static com.evilbird.warcraft.object.unit.UnitArchetype.CommandCentre;
import static com.evilbird.warcraft.object.unit.UnitArchetype.ConjuredEffect;
import static com.evilbird.warcraft.object.unit.UnitArchetype.ConjuredUnit;
import static com.evilbird.warcraft.object.unit.UnitArchetype.Critter;
import static com.evilbird.warcraft.object.unit.UnitArchetype.Demolition;
import static com.evilbird.warcraft.object.unit.UnitArchetype.Flying;
import static com.evilbird.warcraft.object.unit.UnitArchetype.FlyingProducer;
import static com.evilbird.warcraft.object.unit.UnitArchetype.FlyingUpgrader;
import static com.evilbird.warcraft.object.unit.UnitArchetype.FoodProducer;
import static com.evilbird.warcraft.object.unit.UnitArchetype.GoldResource;
import static com.evilbird.warcraft.object.unit.UnitArchetype.HeavyWarship;
import static com.evilbird.warcraft.object.unit.UnitArchetype.NavalProducer;
import static com.evilbird.warcraft.object.unit.UnitArchetype.NavalUpgrader;
import static com.evilbird.warcraft.object.unit.UnitArchetype.OilDepot;
import static com.evilbird.warcraft.object.unit.UnitArchetype.OilProducer;
import static com.evilbird.warcraft.object.unit.UnitArchetype.OilResource;
import static com.evilbird.warcraft.object.unit.UnitArchetype.SiegeEngine;
import static com.evilbird.warcraft.object.unit.UnitArchetype.Submarine;
import static com.evilbird.warcraft.object.unit.UnitArchetype.Tanker;
import static com.evilbird.warcraft.object.unit.UnitArchetype.Tower;
import static com.evilbird.warcraft.object.unit.UnitArchetype.Transportation;
import static com.evilbird.warcraft.object.unit.UnitArchetype.Warrior;
import static com.evilbird.warcraft.object.unit.UnitArchetype.Warship;
import static com.evilbird.warcraft.object.unit.UnitArchetype.Waypoint;
import static com.evilbird.warcraft.object.unit.UnitArchetype.Wizard;
import static com.evilbird.warcraft.object.unit.UnitArchetype.WizardProducer;
import static com.evilbird.warcraft.object.unit.UnitArchetype.WoodDepot;
import static com.evilbird.warcraft.object.unit.UnitArchetype.Worker;
import static com.evilbird.warcraft.object.unit.UnitAttack.Explosive;
import static com.evilbird.warcraft.object.unit.UnitAttack.Magic;
import static com.evilbird.warcraft.object.unit.UnitAttack.Melee;
import static com.evilbird.warcraft.object.unit.UnitAttack.Naval;
import static com.evilbird.warcraft.object.unit.UnitAttack.None;
import static com.evilbird.warcraft.object.unit.UnitAttack.Ranged;
import static com.evilbird.warcraft.object.unit.UnitAttack.Siege;
import static com.evilbird.warcraft.object.unit.UnitSize.ExtraLarge;
import static com.evilbird.warcraft.object.unit.UnitSize.ExtraSmall;
import static com.evilbird.warcraft.object.unit.UnitSize.Large;
import static com.evilbird.warcraft.object.unit.UnitSize.Medium;
import static com.evilbird.warcraft.object.unit.UnitSize.Small;
import static com.evilbird.warcraft.object.unit.UnitSize.SubMedium;
import static com.evilbird.warcraft.object.unit.UnitSize.SuperMedium;

/**
 * Defines identifiers for items varieties.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("checkstyle:MethodParamPad")
public enum UnitType implements GameObjectType
{
    /* Human Buildings         | Faction   | Archetype         | Attack    | Size   */
    Barracks                    (Human,     CombatantProducer,  None,       Large),
    Blacksmith                  (Human,     CombatantUpgrader,  None,       Large),
    CannonTower                 (Human,     Tower,              Siege,      Small),
    Castle                      (Human,     CommandCentre,      None,       ExtraLarge),
    Church                      (Human,     CavalryUpgrader,    None,       Large),
    Farm                        (Human,     FoodProducer,       None,       Small),
    Foundry                     (Human,     NavalUpgrader,      None,       Large),
    GnomishInventor             (Human,     FlyingUpgrader,     None,       Large),
    GryphonAviary               (Human,     FlyingProducer,     None,       Large),
    GuardTower                  (Human,     Tower,              Ranged,     Small),
    Keep                        (Human,     CommandCentre,      None,       ExtraLarge),
    LumberMill                  (Human,     WoodDepot,          None,       Large),
    MageTower                   (Human,     WizardProducer,     None,       Large),
    OilPlatform                 (Human,     OilProducer,        None,       Large),
    Refinery                    (Human,     OilDepot,           None,       Large),
    ScoutTower                  (Human,     Tower,              None,       Small),
    Shipyard                    (Human,     NavalProducer,      None,       Large),
    Stables                     (Human,     CavalryRequisite,   None,       Large),
    TownHall                    (Human,     CommandCentre,      None,       ExtraLarge),

    /* Human - Combatant */
    Ballista                    (Human,     SiegeEngine,        Siege,      Small),
    Battleship                  (Human,     HeavyWarship,       Naval,      SuperMedium),
    DwarvenDemolitionSquad      (Human,     Demolition,         Explosive,  ExtraSmall),
    ElvenArcher                 (Human,     Archer,             Ranged,     ExtraSmall),
    ElvenDestroyer              (Human,     Warship,            Naval,      SuperMedium),
    ElvenRanger                 (Human,     Archer,             Ranged,     ExtraSmall),
    Footman                     (Human,     Warrior,            Melee,      ExtraSmall),
    GnomishFlyingMachine        (Human,     Flying,             None,       Medium),
    GnomishSubmarine            (Human,     Submarine,          Naval,      SubMedium),
    GryphonRider                (Human,     Flying,             Ranged,     Medium),
    Knight                      (Human,     Cavalry,            Melee,      ExtraSmall),
    Mage                        (Human,     Wizard,             Magic,      ExtraSmall),
    OilTanker                   (Human,     Tanker,             None,       SubMedium),
    Paladin                     (Human,     Cavalry,            Melee,      ExtraSmall),
    Peasant                     (Human,     Worker,             Melee,      ExtraSmall),
    Transport                   (Human,     Transportation,     None,       SubMedium),

    /* Human Special Combatant */
    AnduinLothar                (Human,     Cavalry,            Melee,      ExtraSmall),
    UtherLightbringer           (Human,     Cavalry,            Melee,      ExtraSmall),

    /* Orc Buildings           | Faction   | Archetype         | Attack    | Size   */
    AltarOfStorms               (Orc,       CavalryRequisite,   None,       Large),
    Forge                       (Orc,       CombatantUpgrader,  None,       Large),
    Encampment                  (Orc,       CombatantProducer,  None,       Large),
    BombardTower                (Orc,       Tower,              Siege,      Small),
    Dockyard                    (Orc,       NavalProducer,      None,       Large),
    DragonRoost                 (Orc,       FlyingProducer,     None,       Large),
    Fortress                    (Orc,       CommandCentre,      None,       ExtraLarge),
    Metalworks                  (Orc,       NavalUpgrader,      None,       Large),
    GoblinAlchemist             (Orc,       FlyingUpgrader,     None,       Large),
    GreatHall                   (Orc,       CommandCentre,      None,       ExtraLarge),
    LookoutTower                (Orc,       Tower,              None,       Small),
    OgreMound                   (Orc,       CavalryRequisite,   None,       Large),
    OilRefinery                 (Orc,       OilDepot,           None,       Large),
    OilRig                      (Orc,       OilProducer,        None,       Large),
    PigFarm                     (Orc,       FoodProducer,       None,       Small),
    Stronghold                  (Orc,       CommandCentre,      None,       ExtraLarge),
    TempleOfTheDamned           (Orc,       WizardProducer,     None,       Large),
    TrollLumberMill             (Orc,       WoodDepot,          None,       Large),
    WatchTower                  (Orc,       Tower,              Ranged,     Small),

    /* Orc Combatant */
    Catapult                    (Orc,       SiegeEngine,        Siege,      Small),
    DeathKnight                 (Orc,       Cavalry,            Magic,      ExtraSmall),
    Dragon                      (Orc,       Flying,             Ranged,     SuperMedium),
    Ferry                       (Orc,       Transportation,     None,       SubMedium),
    GiantTurtle                 (Orc,       Submarine,          Naval,      SubMedium),
    GoblinSappers               (Orc,       Demolition,         Explosive,  ExtraSmall),
    GoblinZeppelin              (Orc,       Flying,             None,       SubMedium),
    Grunt                       (Orc,       Warrior,            Melee,      ExtraSmall),
    Ogre                        (Orc,       Cavalry,            Melee,      ExtraSmall),
    OgreJuggernaught            (Orc,       HeavyWarship,       Naval,      SuperMedium),
    OgreMage                    (Orc,       Cavalry,            Melee,      ExtraSmall),
    Peon                        (Orc,       Worker,             Melee,      ExtraSmall),
    TrollAxethrower             (Orc,       Archer,             Ranged,     ExtraSmall),
    TrollBerserker              (Orc,       Archer,             Ranged,     ExtraSmall),
    TrollDestroyer              (Orc,       Warship,            Naval,      SuperMedium),
    TrollTanker                 (Orc,       Tanker,             None,       SubMedium),

    /* Orc Special Combatant */
    Chogall                     (Orc,       Cavalry,            Melee,      ExtraSmall),
    Guldan                      (Orc,       Cavalry,            Magic,      ExtraSmall),
    Zuljin                      (Orc,       Archer,             Ranged,     ExtraSmall),

    /* Neutral Buildings       | Faction   | Archetype         | Attack    | Size   */
    CircleOfPower               (Neutral,   Waypoint,           None,       Small),
    DarkPortal                  (Neutral,   Waypoint,           None,       ExtraLarge),
    Runestone                   (Neutral,   Waypoint,           None,       Small),

    /* Neutral Combatant */
    Daemon                      (Neutral,   ConjuredUnit,       Magic,      SubMedium),
    Boar                        (Neutral,   Critter,            Melee,      ExtraSmall),
    Seal                        (Neutral,   Critter,            Melee,      ExtraSmall),
    Sheep                       (Neutral,   Critter,            Melee,      ExtraSmall),

    /* Conjured Combatant */
    Blizzard                    (Neutral,   ConjuredEffect,     Magic,      ExtraLarge),
    DeathAndDecay               (Neutral,   ConjuredEffect,     Magic,      SuperMedium),
    EyeOfKilrogg                (Neutral,   ConjuredUnit,       None,       ExtraSmall),
    FlameShield                 (Neutral,   ConjuredEffect,     Magic,      ExtraLarge),
    RuneTrap                    (Neutral,   ConjuredEffect,     Explosive,  SuperMedium),
    Skeleton                    (Neutral,   ConjuredUnit,       Melee,      ExtraSmall),
    Whirlwind                   (Neutral,   ConjuredEffect,     Magic,      ExtraLarge),

    /* Neutral Resource */
    GoldMine                    (Neutral,   GoldResource,       None,       Large),
    OilPatch                    (Neutral,   OilResource,        None,       Large);

    private UnitAttack attack;
    private UnitArchetype archetype;
    private WarcraftFaction faction;
    private UnitSize size;

    UnitType(WarcraftFaction faction, UnitArchetype archetype, UnitAttack attack, UnitSize size) {
        this.faction = faction;
        this.archetype = archetype;
        this.attack = attack;
        this.size = size;
    }

    public UnitArchetype getArchetype() {
        return archetype;
    }

    public UnitAttack getAttack() {
        return attack;
    }

    public WarcraftFaction getFaction() {
        return faction;
    }

    public UnitSize getSize() {
        return size;
    }
}
