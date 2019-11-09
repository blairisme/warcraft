/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit;

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
public enum UnitType implements ItemType
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
    AnduinLothar,
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

    /* Conjured - Combatant */
    Blizzard,
    DeathAndDecay,
    EyeOfKilrogg,
    FlameShield,
    RuneTrap,
    Skeleton,
    Whirlwind,

    /* Neutral - Resource */
    GoldMine,
    OilPatch;

    public UnitAttack getAttack() {
        if (isCombatant()) {
            if (isNavalUnit()) {
                return Ship;
            }
            if (isSiege()) {
                return Siege;
            }
            if (isRanged()) {
                return Ranged;
            }
            if (isSpellCaster()) {
                return Magic;
            }
            return Melee;
        }
        return None;
    }

    public UnitType getBase() {
        switch (this) {
            case AnduinLothar: return Knight;
            case UtherLightbringer: return Paladin;
            case Chogall: return OgreMage;
            case Guldan: return DeathKnight;
            case Zuljin: return TrollAxethrower;
            default: return this;
        }
    }

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

    public boolean isBuilding() {
        return isBetween(this, Barracks, TownHall)
            || isBetween(this, AltarOfStorms, WatchTower)
            || isBetween(this, CircleOfPower, Runestone);
    }

    public boolean isTower() {
        return this == ScoutTower || this == GuardTower || this == CannonTower
            || this == WatchTower || this == LookoutTower || this == BombardTower;
    }

    public boolean isOffensiveTower() {
        return this == GuardTower || this == CannonTower
            || this == LookoutTower || this == BombardTower;
    }

    public boolean isCombatant() {
        return isBetween(this, Ballista, UtherLightbringer) || isBetween(this, Catapult, Zuljin);
    }

    public boolean isDemoTeam() {
        return this == DwarvenDemolitionSquad || this == GoblinSappers;
    }

    public boolean isGatherer() {
        return this == Peasant || this == Peon || this == OilTanker || this == TrollTanker;
    }

    public boolean isResource() {
        return this == GoldMine || this == OilPatch;
    }

    public boolean isResourceExtractor() {
        return this == OilRig || this == OilPlatform;
    }

    public boolean isSpecial() {
        return isBetween(this, AnduinLothar, UtherLightbringer) || isBetween(this, Chogall, Zuljin);
    }

    public boolean isNaval() {
        return isNavalBuilding() || isNavalUnit();
    }

    public boolean isNavalBuilding() {
        return this == Shipyard || this == OilPlatform || this == OilRefinery || this == Foundry
                || this == Dockyard || this== OilRig || this == Refinery || this == Metalworks;
    }

    public boolean isNavalUnit() {
        return this == ElvenDestroyer || this == Battleship || this == GnomishSubmarine
            || this == OilTanker || this == Transport
            || this == TrollDestroyer || this == OgreJuggernaught || this == GiantTurtle
            || this == TrollTanker || this == Ferry;
    }

    public boolean isSubmarine() {
        return this == GnomishSubmarine || this == GiantTurtle;
    }

    public boolean isTransport() {
        return this == Transport || this == Ferry;
    }

    public boolean isSiege() {
        return this == Ballista || this == Catapult;
    }

    public boolean isFlying() {
        return isFlyingAssault() || isScout();
    }

    public boolean isFlyingAssault() {
        return this == GryphonRider || this == Dragon || this == Daemon;
    }

    public boolean isScout() {
        return this == GnomishFlyingMachine || this == GoblinZeppelin;
    }

    public boolean isMelee() {
        return this == Footman || this == Knight || this == Paladin || this == Peasant
            || this == Grunt || this == Ogre || this == OgreMage || this == Peon
            || this == AnduinLothar || this == UtherLightbringer;
    }

    public boolean isRanged() {
        return this == ElvenArcher || this == ElvenRanger
            || this == TrollAxethrower || this == TrollBerserker
            || this == Zuljin;
    }

    public boolean isSpellCaster() {
        return this == Mage || this == DeathKnight
            || this == Paladin || this == OgreMage;
    }

    public boolean isConjured() {
        return this == Daemon || this == Skeleton || this == EyeOfKilrogg;
    }

    public boolean isFoodProducer() {
        return this == Farm || this == PigFarm;
    }

    public boolean isCommandCentre() {
        return this == TownHall || this == Keep || this == Castle
            || this == GreatHall || this == Stronghold || this == Fortress;
    }

    public boolean isGoldDepot() {
        return isCommandCentre();
    }

    public boolean isOilDepot() {
        return this == Shipyard || this == Dockyard
            || this == Refinery || this == OilRefinery;
    }

    public boolean isWoodDepot() {
        return isCommandCentre() || this == LumberMill || this == TrollLumberMill;
    }

    public boolean isCritter() {
        return this == Boar || this == Seal || this == Sheep;
    }

    public boolean isHuman() {
        return getFaction() == Human;
    }

    public boolean isOrc() {
        return getFaction() == Orc;
    }

    public boolean isNeutral() {
        return getFaction() == Neutral;
    }
}