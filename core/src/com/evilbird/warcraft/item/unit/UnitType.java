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

/**
 * Defines identifiers for items varieties.
 *
 * @author Blair Butterworth
 */
@SerializedType("Units")
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
    ElvenArcherCaptive,

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
    Peon,
    TrollAxethrower,
    TrollDestroyer,
    TrollTanker,

    /* Orc - Special Combatant */
    TrollAxethrowerCaptive,
    Zuljin,

    /* Neutral - Building */
    CircleOfPower,
    Runestone,

    /* Neutral - Combatant */
    Pig,
    Seal,
    Sheep,

    /* Neutral - Resource */
    GoldMine,
    OilPatch;

    public UnitFaction getFaction() {
        if (isBetween(Barracks, ElvenArcherCaptive)) {
            return UnitFaction.Human;
        }
        if (isBetween(AltarOfStorms, Zuljin)) {
            return UnitFaction.Orc;
        }
        if (isBetween(CircleOfPower, OilPatch)) {
            return UnitFaction.Neutral;
        }
        throw new UnsupportedOperationException();
    }

    private boolean isBetween(UnitType typeA, UnitType typeB) {
        return this.ordinal() >= typeA.ordinal() && this.ordinal() <= typeB.ordinal();
    }
}