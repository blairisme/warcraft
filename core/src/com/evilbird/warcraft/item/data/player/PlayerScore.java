/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.data.player;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.UnitType;

/**
 * Instances of this class defines the player score value obtained when killing
 * different unit types.
 *
 * @author Blair Butterworth
 */
public class PlayerScore
{
    /**
     * Disable construction of static utility class.
     */
    private PlayerScore() {
    }

    /**
     * Obtains the score value associated with the given {@link Item},
     * presumably a victim of a players actions. Score values are
     * returned as noted in the table below.
     *
     * <pre>
     * Wall 	                1       Tower 	                95
     * Critter 	                1       Farm 	                100
     * Peasant/Peon 	        30      Lumber mill 	        150
     * Flying Machine/Zeppelin 	40      Runestone               150
     * Tanker 	                40      Barracks                160
     * Footman/Grunt 	        50      Oil Rig                 160
     * Transport                50      Blacksmith              170
     * Archer/Axe Thrower       60      Shipyard                170
     * Ranger/Berserker         70      Foundry                 200
     * Dwarves/Sappers 	        100 	Guard Tower 	        200
     * Knight/Ogre              100 	Refinery                200
     * Ballista/Catapult        100 	Town Hall               200
     * Mage/Death Knight        100 	Stables/Ogre Mound      210
     * Demon 	                100 	Inventor/Alchemist      230
     * Paladin/Ogre Mage        110 	Church/Altar 	        240
     * Legendary Hero 	        120 	Wizard's Tower/Temple 	240
     * Submarine/Turtle         120 	Cannon Tower 	        250
     * Destroyer                150 	Aviary/Roost 	        280
     * Gryphon/Dragon 	        150 	Keep/Stronghold         600
     * Battleship/Juggernaut    300     Castle/Fortress         1500
     * </pre>
     *
     * @param item  the {@code Item} whose score will be returned.
     * @return      a score value following the table above.
     */
    public static int getScoreValue(Item item) {
        UnitType type = (UnitType)item.getType();
        switch (type) {
            case Peasant: return 30;
            case Farm: return 100;
            case Footman:
            case Grunt: return 50;
            case Barracks: return 160;
            case TownHall: return 200;
            default: return 0;
        }
    }
}
