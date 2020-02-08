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
import static com.evilbird.warcraft.object.unit.UnitArchetype.CombatantProducer;
import static com.evilbird.warcraft.object.unit.UnitArchetype.CommandCentre;
import static com.evilbird.warcraft.object.unit.UnitArchetype.FlyingProducer;
import static com.evilbird.warcraft.object.unit.UnitArchetype.FlyingUpgrader;
import static com.evilbird.warcraft.object.unit.UnitArchetype.NavalProducer;
import static com.evilbird.warcraft.object.unit.UnitArchetype.Tanker;
import static com.evilbird.warcraft.object.unit.UnitArchetype.WizardProducer;
import static com.evilbird.warcraft.object.unit.UnitArchetype.Worker;

/**
 * Defines the resources and times required to produce a unit.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("checkstyle:MethodParamPad")
public enum UnitProduction
{
    /*                      | Facility  | Cost                          | Time      */
    Unproducible            (null,      Resources(0, 0, 0, 0),          ZERO),

    Barracks        		(Worker,    Resources(700, 450, 0, 0),    	Seconds(70)),
    Blacksmith      		(Worker,    Resources(800, 450, 100, 0),  	Seconds(60)),
    CannonTower     		(Worker,    Resources(1000, 300, 0, 0),   	Seconds(190)),
    Castle          		(Worker,    Resources(2500, 1200, 500, 0),  Seconds(50)),
    Church          		(Worker,    Resources(900, 500, 0, 0),    	Seconds(60)),
    Farm            		(Worker,    Resources(500, 250, 0, 0),    	Seconds(30)),
    Foundry         		(Worker,    Resources(700, 400, 400, 0),  	Seconds(60)),
    GnomishInventor 		(Worker,    Resources(1000, 400, 0, 0),   	Seconds(150)),
    GryphonAviary   		(Worker,    Resources(1000, 400, 0, 0),   	Seconds(150)),
    GuardTower      		(Worker,    Resources(500, 150, 0, 0),    	Seconds(40)),
    Keep            		(Worker,    Resources(2000, 1000, 200, 0),	Seconds(60)),
    LumberMill      		(Worker,    Resources(600, 450, 0, 0),    	Seconds(45)),
    MageTower       		(Worker,    Resources(1000, 200, 0, 0),   	Seconds(40)),
    OilPlatform     		(Tanker,    Resources(700, 450, 0, 0),    	Seconds(200)),
    Refinery        		(Worker,    Resources(800, 350, 200, 0),  	Seconds(225)),
    ScoutTower      		(Worker,    Resources(550, 200, 0, 0),    	Seconds(20)),
    Shipyard        		(Worker,    Resources(800, 450, 0, 0),    	Seconds(200)),
    Stables         		(Worker,    Resources(1000, 300, 0, 0),   	Seconds(60)),
    TownHall        		(Worker,    Resources(1200, 800, 0, 0),   	Seconds(80)),

    AltarOfStorms           (Worker,    Resources(900, 500, 0, 0),      Seconds(60)),
    BombardTower            (Worker,    Resources(1000, 300, 0, 0),     Seconds(190)),
    Dockyard                (Worker,    Resources(800, 450, 0, 0),      Seconds(60)),
    DragonRoost             (Worker,    Resources(1000, 400, 0, 0),     Seconds(150)),
    Encampment              (Worker,    Resources(700, 450, 0, 0),      Seconds(70)),
    Forge                   (Worker,    Resources(800, 450, 100, 0),    Seconds(60)),
    Fortress                (Worker,    Resources(2500, 1200, 500, 0),  Seconds(80)),
    Metalworks              (Worker,    Resources(700, 400, 400, 0),    Seconds(60)),
    GoblinAlchemist    	    (Worker,    Resources(1000, 400, 0, 0),     Seconds(150)),
    GreatHall          		(Worker,    Resources(1200, 800, 0, 0),     Seconds(80)),
    LookoutTower       		(Worker,    Resources(550, 200, 0, 0),      Seconds(40)),
    OgreMound          		(Worker,    Resources(1000, 300, 0, 0),     Seconds(60)),
    OilRefinery        		(Worker,    Resources(800, 350, 200, 0),    Seconds(80)),
    OilRig             		(Worker,    Resources(700, 450, 0, 0),      Seconds(200)),
    PigFarm            		(Worker,    Resources(500, 250, 0, 0),      Seconds(30)),
    Stronghold         		(Worker,    Resources(2000, 1000, 200, 0),  Seconds(60)),
    TempleOfTheDamned  		(Worker,    Resources(1000, 200, 0, 0),     Seconds(40)),
    TrollLumberMill    		(Worker,    Resources(600, 450, 0, 0),      Seconds(45)),
    WatchTower         		(Worker,    Resources(500, 150, 0, 0),      Seconds(20)),

    /*                      | Facility          | Cost                          | Time      */
    Ballista                (CombatantProducer, Resources(900, 300, 0, 1),    	Seconds(250)),
    Battleship              (NavalProducer,     Resources(1000, 500, 1000, 1),  Seconds(140)),
    DwarvenDemolitionSquad  (CombatantProducer, Resources(750, 250, 0, 1),    	Seconds(200)),
    ElvenArcher             (CombatantProducer, Resources(500, 50, 0, 1),     	Seconds(70)),
    ElvenDestroyer          (NavalProducer,     Resources(700, 350, 700, 1),  	Seconds(90)),
    ElvenRanger             (CombatantProducer, Resources(500, 50, 0, 1),     	Seconds(70)),
    Footman                 (CombatantProducer, Resources(600, 0, 0, 1),      	Seconds(60)),
    GnomishFlyingMachine    (FlyingUpgrader,    Resources(500, 100, 0, 1),    	Seconds(65)),
    GnomishSubmarine        (NavalProducer,     Resources(800, 150, 800, 1),  	Seconds(100)),
    GryphonRider            (FlyingProducer,    Resources(2500, 0, 0, 1),     	Seconds(250)),
    Knight                  (CombatantProducer, Resources(800, 100, 0, 1),    	Seconds(90)),
    Mage                    (WizardProducer,    Resources(1200, 0, 0, 1),     	Seconds(30)),
    OilTanker               (NavalProducer,     Resources(400, 250, 0, 1),    	Seconds(50)),
    Paladin                 (CombatantProducer, Resources(800, 100, 0, 1),    	Seconds(90)),
    Peasant                 (CommandCentre,     Resources(400, 0, 0, 1),        Seconds(45)),
    Transport               (NavalProducer,     Resources(600, 200, 500, 1),    Seconds(70)),

    Catapult                (CombatantProducer, Resources(900, 300, 0, 1),      Seconds(250)),
    DeathKnight             (WizardProducer,    Resources(1200, 0, 0, 1),       Seconds(120)),
    Dragon                  (FlyingProducer,    Resources(2500, 0, 0, 1),       Seconds(250)),
    Ferry                   (NavalProducer,     Resources(600, 200, 500, 1),    Seconds(70)),
    GiantTurtle             (NavalProducer,     Resources(800, 150, 800, 1),    Seconds(100)),
    GoblinSappers           (FlyingUpgrader,    Resources(750, 250, 0, 1),      Seconds(200)),
    GoblinZeppelin          (FlyingUpgrader,    Resources(500, 100, 0, 1),      Seconds(65)),
    Grunt                   (CombatantProducer, Resources(600, 0, 0, 1),        Seconds(60)),
    Ogre                    (CombatantProducer, Resources(800, 100, 0, 1),      Seconds(90)),
    OgreJuggernaught        (NavalProducer,     Resources(1000, 500, 1000, 1),  Seconds(140)),
    OgreMage                (CombatantProducer, Resources(800, 100, 0, 1),      Seconds(90)),
    Peon                    (CommandCentre,     Resources(400, 0, 0, 1),        Seconds(10)),
    TrollAxethrower         (CombatantProducer, Resources(500, 50, 0, 1),       Seconds(70)),
    TrollBerserker          (CombatantProducer, Resources(500, 50, 0, 1),       Seconds(70)),
    TrollDestroyer          (NavalProducer,     Resources(700, 350, 700, 1),    Seconds(90)),
    TrollTanker             (NavalProducer,     Resources(400, 250, 0, 1),      Seconds(50));

    private UnitArchetype producer;
    private ResourceSet cost;
    private Duration duration;

    UnitProduction(UnitArchetype producer, ResourceSet cost, Duration duration) {
        this.producer = producer;
        this.cost = cost;
        this.duration = duration;
    }

    /**
     * Returns the resource cost of producing the unit.
     */
    public ResourceSet getCost() {
        return cost;
    }

    /**
     * Returns the time required to produce a unit.
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * Returns the facility required to produce a unit.
     */
    public UnitArchetype getProducer() {
        return producer;
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
