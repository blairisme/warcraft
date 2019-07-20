/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.common.WarcraftFaction;

import static com.evilbird.engine.common.collection.EnumUtils.isBetween;
import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Neutral;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;
import static com.evilbird.warcraft.item.unit.UnitAttack.Magic;
import static com.evilbird.warcraft.item.unit.UnitAttack.Melee;
import static com.evilbird.warcraft.item.unit.UnitAttack.None;
import static com.evilbird.warcraft.item.unit.UnitAttack.Ranged;
import static com.evilbird.warcraft.item.unit.UnitAttack.Ship;
import static com.evilbird.warcraft.item.unit.UnitAttack.Siege;

/**
 * Defines identifiers for items varieties.
 *
 * @author Blair Butterworth
 */
@SerializedType("Units")
public enum UnitType implements ItemType//, ParameterizedSupplier<String, String>
{
    /* Human - Building */
    Barracks,
    Blacksmith,
    CannonTower,
    Castle,
    Church,
    Farm,
    Foundry,
    GnomishInventor,
    GryphonAviary,
    GuardTower,
    Keep,
    LumberMill,
    MageTower,
    OilPlatform,
    Refinery,
    ScoutTower,
    Shipyard,
    Stables,
    TownHall,

    /* Human - Combatant */
    Ballista,
    Battleship,
    DwarvenDemolitionSquad,
    ElvenArcher,
    ElvenDestroyer,
    ElvenRanger,
    Footman,
    GnomishFlyingMachine,
    GnomishSubmarine,
    GryphonRider,
    Knight,
    Mage,
    OilTanker,
    Paladin,
    Peasant,
    Transport,

    /* Human - Special Combatant */
    AlteracTraitor,
    AnduinLothar,
    ElvenArcherCaptive,
    MageCaptive,
    PeasantCaptive,
    UtherLightbringer,

    /* Orc - Building */
    AltarOfStorms,
    Forge,
    Encampment,
    BombardTower,
    Dockyard,
    DragonRoost,
    Fortress,
    Metalworks,
    GoblinAlchemist,
    GreatHall,
    LookoutTower,
    OgreMound,
    OilRefinery,
    OilRig,
    PigFarm,
    Stronghold,
    TempleOfTheDamned,
    TrollLumberMill,
    WatchTower,

    /* Orc - Combatant */
    Catapult,
    DeathKnight,
    Dragon,
    EyeOfKilrogg,
    Ferry,
    GiantTurtle,
    GoblinSappers,
    GoblinZeppelin,
    Grunt,
    Ogre,
    OgreJuggernaught,
    OgreMage,
    Peon,
    TrollAxethrower,
    TrollBerserker,
    TrollDestroyer,
    TrollTanker,

    /* Orc - Special Combatant */
    Chogall,
    Guldan,
    TrollAxethrowerCaptive,
    Zuljin,

    /* Neutral - Building */
    CircleOfPower,
    DarkPortal,
    Runestone,

    /* Neutral - Combatant */
    Daemon,
    Boar,
    Seal,
    Sheep,

    /* Neutral - Resource */
    GoldMine,
    OilPatch;

//    public String get(String key) {
//        if (key.equals("name")) {
//            return CaseUtils.toSnakeCase(name());
//        }
//        if (key.equals("faction")) {
//            return CaseUtils.toSnakeCase(getFaction().name());
//        }
//        if (key.equals("attack")) {
//            return CaseUtils.toSnakeCase(getAttack().name());
//        }
//        return null;
//    }

    public WarcraftFaction getFaction() {
        if (isBetween(this, Barracks, UtherLightbringer)) {
            return Human;
        }
        if (isBetween(this, AltarOfStorms, Zuljin)) {
            return Orc;
        }
        if (isBetween(this, CircleOfPower, OilPatch)) {
            return Neutral;
        }
        throw new UnsupportedOperationException();
    }

    public UnitAttack getAttack() {
        if (isCombatant()) {
            if (isShip()) {
                return Ship;
            }
            if (isSiege()) {
                return Siege;
            }
            if (isRanged()) {
                return Ranged;
            }
            if (isMagic()) {
                return Magic;
            }
            return Melee;
        }
        return None;
    }

    public boolean isBuilding() {
        return isBetween(this, Barracks, TownHall) || isBetween(this, AltarOfStorms, WatchTower);
    }

    public boolean isCombatant() {
        return isBetween(this, Ballista, UtherLightbringer) || isBetween(this, Catapult, Zuljin);
    }

    public boolean isSpecial() {
        return isBetween(this, AlteracTraitor, UtherLightbringer) || isBetween(this, Chogall, Zuljin);
    }

    public boolean isShip() {
        return this == ElvenDestroyer || this == Battleship || this == GnomishSubmarine || this == OilTanker
            || this == TrollDestroyer || this == OgreJuggernaught || this == GiantTurtle || this == TrollTanker;
    }

    public boolean isSiege() {
        return this == Ballista || this == Catapult;
    }

    public boolean isRanged() {
        return this == ElvenArcher || this == ElvenArcherCaptive || this == ElvenRanger
            || this == TrollAxethrower || this == TrollAxethrowerCaptive || this == TrollBerserker
            || this == GryphonRider || this == Dragon || this == Zuljin;
    }

    public boolean isMagic() {
        return this == Mage || this == Paladin
            || this == DeathKnight || this == OgreMage;
    }

    public boolean isHuman() {
        return getFaction() == Human;
    }

    public boolean isOrc() {
        return getFaction() == Orc;
    }
}