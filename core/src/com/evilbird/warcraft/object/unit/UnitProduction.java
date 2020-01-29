/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit;

import com.evilbird.engine.common.time.Duration;
import com.evilbird.warcraft.data.resource.ResourceSet;
import org.apache.commons.lang3.EnumUtils;

import static com.evilbird.engine.common.time.Duration.ZERO;
import static com.evilbird.engine.common.time.DurationUtils.Seconds;
import static com.evilbird.warcraft.data.resource.ResourceOperations.Resources;

/**
 * Defines the resources and times required to produce a unit.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("checkstyle:MethodParamPad")
public enum UnitProduction
{
    /*                     | Production Cost                   | Production Time  */
    Unproducible            (Resources(0, 0, 0, 0),        		ZERO),

    Barracks        		(Resources(700, 450, 0, 0),    		Seconds(70)), //71
    Blacksmith      		(Resources(800, 450, 100, 0),  		Seconds(60)), //107
    CannonTower     		(Resources(1000, 300, 0, 0),   		Seconds(190)),
    Castle          		(Resources(2500, 1200, 500, 0),		Seconds(50)), //58
    Church          		(Resources(900, 500, 0, 0),    		Seconds(60)), //60
    Farm            		(Resources(500, 250, 0, 0),    		Seconds(30)), //31
    Foundry         		(Resources(700, 400, 400, 0),  		Seconds(60)), //56
    GnomishInventor 		(Resources(1000, 400, 0, 0),   		Seconds(150)),
    GryphonAviary   		(Resources(1000, 400, 0, 0),   		Seconds(150)),
    GuardTower      		(Resources(500, 150, 0, 0),    		Seconds(40)), //38
    Keep            		(Resources(2000, 1000, 200, 0),		Seconds(60)), //54
    LumberMill      		(Resources(600, 450, 0, 0),    		Seconds(45)), //48
    MageTower       		(Resources(1000, 200, 0, 0),   		Seconds(40)), //39
    OilPlatform     		(Resources(700, 450, 0, 0),    		Seconds(200)),
    Refinery        		(Resources(800, 350, 200, 0),  		Seconds(225)),
    ScoutTower      		(Resources(550, 200, 0, 0),    		Seconds(20)), //20
    Shipyard        		(Resources(800, 450, 0, 0),    		Seconds(200)),
    Stables         		(Resources(1000, 300, 0, 0),   		Seconds(60)), //59
    TownHall        		(Resources(1200, 800, 0, 0),   		Seconds(80)), //83

    Ballista                (Resources(900, 300, 0, 1),    		Seconds(250)),
    Battleship              (Resources(1000, 500, 1000, 1),		Seconds(140)),
    DwarvenDemolitionSquad  (Resources(750, 250, 0, 1),    		Seconds(200)),
    ElvenArcher             (Resources(500, 50, 0, 1),     		Seconds(70)),
    ElvenDestroyer          (Resources(700, 350, 700, 1),  		Seconds(90)),
    ElvenRanger             (Resources(500, 50, 0, 1),     		Seconds(70)),
    Footman                 (Resources(600, 0, 0, 1),      		Seconds(60)),
    GnomishFlyingMachine    (Resources(500, 100, 0, 1),    		Seconds(65)),
    GnomishSubmarine        (Resources(800, 150, 800, 1),  		Seconds(100)),
    GryphonRider            (Resources(2500, 0, 0, 1),     		Seconds(250)),
    Knight                  (Resources(800, 100, 0, 1),    		Seconds(90)),
    Mage                    (Resources(1200, 0, 0, 1),     		Seconds(30)), //32
    OilTanker               (Resources(400, 250, 0, 1),    		Seconds(50)),
    Paladin                 (Resources(800, 100, 0, 1),    		Seconds(90)),
    Peasant                 (Resources(400, 0, 0, 1),          	Seconds(45)),
    Transport               (Resources(600, 200, 500, 1),      	Seconds(70)),

    AltarOfStorms           (Resources(900, 500, 0, 0),         Seconds(60)), //62
    BombardTower            (Resources(1000, 300, 0, 0),        Seconds(190)),
    Dockyard                (Resources(800, 450, 0, 0),         Seconds(60)), //65
    DragonRoost             (Resources(1000, 400, 0, 0),        Seconds(150)),
    Encampment              (Resources(700, 450, 0, 0),         Seconds(70)), //71
    Forge                   (Resources(800, 450, 100, 0),       Seconds(60)), //67
    Fortress                (Resources(2500, 1200, 500, 0),     Seconds(80)), //86
    Metalworks              (Resources(700, 400, 400, 0),       Seconds(60)), //56
    GoblinAlchemist    	    (Resources(1000, 400, 0, 0),        Seconds(150)),
    GreatHall          		(Resources(1200, 800, 0, 0),        Seconds(80)), //83
    LookoutTower       		(Resources(550, 200, 0, 0),         Seconds(40)), //38
    OgreMound          		(Resources(1000, 300, 0, 0),        Seconds(60)), //59
    OilRefinery        		(Resources(800, 350, 200, 0),       Seconds(80)), //91
    OilRig             		(Resources(700, 450, 0, 0),         Seconds(200)),
    PigFarm            		(Resources(500, 250, 0, 0),         Seconds(30)), //31
    Stronghold         		(Resources(2000, 1000, 200, 0),     Seconds(60)), //59
    TempleOfTheDamned  		(Resources(1000, 200, 0, 0),        Seconds(40)), //45
    TrollLumberMill    		(Resources(600, 450, 0, 0),         Seconds(45)), //48
    WatchTower         		(Resources(500, 150, 0, 0),         Seconds(20)), //20

    Catapult                (Resources(900, 300, 0, 1),         Seconds(250)),
    DeathKnight             (Resources(1200, 0, 0, 1),          Seconds(120)),
    Dragon                  (Resources(2500, 0, 0, 1),          Seconds(250)),
    Ferry                   (Resources(600, 200, 500, 1),       Seconds(70)),
    GiantTurtle             (Resources(800, 150, 800, 1),       Seconds(100)),
    GoblinSappers           (Resources(750, 250, 0, 1),         Seconds(200)),
    GoblinZeppelin          (Resources(500, 100, 0, 1),         Seconds(65)),
    Grunt                   (Resources(600, 0, 0, 1),           Seconds(60)),
    Ogre                    (Resources(800, 100, 0, 1),         Seconds(90)),
    OgreJuggernaught        (Resources(1000, 500, 1000, 1),     Seconds(140)),
    OgreMage                (Resources(800, 100, 0, 1),         Seconds(90)),
    Peon                    (Resources(400, 0, 0, 1),           Seconds(10)), //12
    TrollAxethrower         (Resources(500, 50, 0, 1),          Seconds(70)),
    TrollBerserker          (Resources(500, 50, 0, 1),          Seconds(70)),
    TrollDestroyer          (Resources(700, 350, 700, 1),       Seconds(90)),
    TrollTanker             (Resources(400, 250, 0, 1),         Seconds(50));

    private ResourceSet cost;
    private Duration duration;

    UnitProduction(ResourceSet cost, Duration duration) {
        this.cost = cost;
        this.duration = duration;
    }

    /**
     * Returns the resource cost of producing the update.
     */
    public ResourceSet getCost() {
        return cost;
    }

    /**
     * Returns the time required to produce an update.
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * Returns a unit production instance defining the resources and times
     * required to produce the given unit.
     */
    public static UnitProduction forProduct(UnitType type) {
        if (EnumUtils.isValidEnum(UnitProduction.class, type.name())) {
            return UnitProduction.valueOf(type.name());
        }
        return Unproducible;
    }
}
