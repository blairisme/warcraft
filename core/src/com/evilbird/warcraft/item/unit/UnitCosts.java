/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.common.collection.Sets;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.data.player.PlayerUpgrade;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.evilbird.warcraft.item.common.resource.ResourceType.Food;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Gold;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Oil;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Wood;

/**
 * Defines the resources and times required to create units.
 *
 * @author Blair Butterworth
 */
public class UnitCosts
{
    private UnitCosts() {
    }

    public static float buildTime(PlayerUpgrade upgrade) {
        if (isQuickBuildEnabled()) {
            return 0;
        }
        switch (upgrade) {
            case RangedDamage1: return 20;
            case RangedDamage2: return 20;
            default: return 0;
        }
    }

    public static float buildTime(Unit unit) {
        return buildTime((UnitType)unit.getType());
    }

    public static float buildTime(UnitType type) {
        if (isQuickBuildEnabled()) {
            return 0;
        }
        if (type.isHuman()) {
            if (type.isBuilding()) {
                return humanBuildingBuildTime(type);
            }
            else if (type.isCombatant()) {
                return humanCombatantBuildTime(type);
            }
        }
        else if (type.isOrc()) {
            if (type.isBuilding()) {
                return orcBuildingBuildTime(type);
            }
            else if (type.isCombatant()) {
                return orcCombatantBuildTime(type);
            }
        }
        return 0;
    }

    private static boolean isQuickBuildEnabled() {
        GameService service = GameService.getInstance();
        WarcraftPreferences preferences = (WarcraftPreferences)service.getPreferences();
        return preferences.isQuickBuildEnabled();
    }

    private static float humanBuildingBuildTime(UnitType type) {
        switch (type) {
            case Barracks: return 200;
            case Blacksmith: return 200;
            case CannonTower: return 190;
            case Castle: return 200;
            case Church: return 175;
            case Farm: return 100;
            case Foundry: return 175;
            case GnomishInventor: return 150;
            case GryphonAviary: return 150;
            case GuardTower: return 140;
            case Keep: return 200;
            case LumberMill: return 150;
            case MageTower: return 125;
            case OilPlatform: return 200;
            case Refinery: return 225;
            case ScoutTower: return 60;
            case Shipyard: return 200;
            case Stables: return 150;
            case TownHall: return 255;
            default: return 0;
        }
    }

    private static float humanCombatantBuildTime(UnitType type) {
        switch (type) {
            case Ballista: return 250;
            case Battleship: return 140;
            case DwarvenDemolitionSquad: return 200;
            case ElvenArcher: return 70;
            case ElvenDestroyer: return 90;
            case ElvenRanger: return 70;
            case Footman: return 60;
            case GnomishFlyingMachine: return 65;
            case GnomishSubmarine: return 100;
            case GryphonRider: return 250;
            case Knight: return 90;
            case Mage: return 120;
            case OilTanker: return 50;
            case Paladin: return 90;
            case Peasant: return 45;
            case Transport: return 70;
            default: return 0;
        }
    }
    
    private static float orcBuildingBuildTime(UnitType type) {
        switch (type) {
            case AltarOfStorms: return 175;
            case Forge: return 200;
            case Encampment: return 200;
            case BombardTower: return 190;
            case Dockyard: return 200;
            case DragonRoost: return 150;
            case Fortress: return 200;
            case Metalworks: return 175;
            case GoblinAlchemist: return 150;
            case GreatHall: return 255;
            case LookoutTower: return 140;
            case OgreMound: return 150;
            case OilRefinery: return 225;
            case OilRig: return 200;
            case PigFarm: return 100;
            case Stronghold: return 200;
            case TempleOfTheDamned: return 125;
            case TrollLumberMill: return 150;
            case WatchTower: return 60;
            default: return 0;
        }
    }

    private static float orcCombatantBuildTime(UnitType type) {
        switch (type) {
            case Catapult: return 250;
            case DeathKnight: return 120;
            case Dragon: return 250;
            case Ferry: return 70;
            case GiantTurtle: return 100;
            case GoblinSappers: return 200;
            case GoblinZeppelin: return 65;
            case Grunt: return 60;
            case Ogre: return 90;
            case OgreJuggernaught: return 140;
            case OgreMage: return 90;
            case Peon: return 45;
            case TrollAxethrower: return 70;
            case TrollBerserker: return 70;
            case TrollDestroyer: return 90;
            case TrollTanker: return 50;
            default: return 0;
        }
    }

    public static Collection<ResourceQuantity> cost(PlayerUpgrade upgrade) {
        if (isFreeBuildEnabled()) {
            return resources(0, 0, 0, 0);
        }
        switch (upgrade) {
            case RangedDamage1: return resources(400, 0, 0, 0);
            case RangedDamage2: return resources(600, 0, 0, 0);
            default: return Collections.emptyList();
        }
    }

    public static Collection<ResourceQuantity> cost(Unit unit) {
        return cost((UnitType)unit.getType());
    }

    public static Collection<ResourceQuantity> cost(UnitType type) {
        if (isFreeBuildEnabled()) {
            return resources(0, 0, 0, 0);
        }
        if (type.isHuman()) {
            if (type.isBuilding()) {
                return humanBuildingCost(type);
            }
            else if (type.isCombatant()) {
                return humanCombatantCost(type);
            }
        }
        else if (type.isOrc()) {
            if (type.isBuilding()) {
                return orcBuildingCost(type);
            }
            else if (type.isCombatant()) {
                return orcCombatantCost(type);
            }
        }
        return Collections.emptySet();
    }

    private static boolean isFreeBuildEnabled() {
        GameService service = GameService.getInstance();
        WarcraftPreferences preferences = (WarcraftPreferences)service.getPreferences();
        return preferences.isFreeBuildEnabled();
    }

    private static Collection<ResourceQuantity> humanBuildingCost(UnitType type) {
        switch (type) {
            case Barracks: return resources(700, 450, 0, 0);
            case Blacksmith: return resources(800, 450, 100, 0);
            case CannonTower: return resources(1000, 300, 0, 0);
            case Castle: return resources(2500, 1200, 500, 0);
            case Church: return resources(900, 500, 0, 0);
            case Farm: return resources(500, 250, 0, 0);
            case Foundry: return resources(700, 400, 400, 0);
            case GnomishInventor: return resources(1000, 400, 0, 0);
            case GryphonAviary: return resources(1000, 400, 0, 0);
            case GuardTower: return resources(500, 150, 0, 0);
            case Keep: return resources(2000, 1000, 200, 0);
            case LumberMill: return resources(600, 450, 0, 0);
            case MageTower: return resources(1000, 200, 0, 0);
            case OilPlatform: return resources(700, 450, 0, 0);
            case Refinery: return resources(800, 350, 200, 0);
            case ScoutTower: return resources(550, 200, 0, 0);
            case Shipyard: return resources(800, 450, 0, 0);
            case Stables: return resources(1000, 300, 0, 0);
            case TownHall: return resources(1200, 800, 0, 0);
            default: return Collections.emptyList();
        }
    }

    private static Collection<ResourceQuantity> humanCombatantCost(UnitType type) {
        switch (type) {
            case Ballista: return resources(900, 300, 0, 1);
            case Battleship: return resources(1000, 500, 1000, 1);
            case DwarvenDemolitionSquad: return resources(750, 250, 0, 1);
            case ElvenArcher: return resources(500, 50, 0, 1);
            case ElvenDestroyer: return resources(700, 350, 700, 1);
            case ElvenRanger: return resources(500, 50, 0, 1);
            case Footman: return resources(600, 0, 0, 1);
            case GnomishFlyingMachine: return resources(500, 100, 0, 1);
            case GnomishSubmarine: return resources(800, 150, 800, 1);
            case GryphonRider: return resources(2500, 0, 0, 1);
            case Knight: return resources(800, 100, 0, 1);
            case Mage: return resources(1200, 0, 0, 1);
            case OilTanker: return resources(400, 250, 0, 1);
            case Paladin: return resources(800, 100, 0, 1);
            case Peasant: return resources(400, 0, 0, 1);
            case Transport: return resources(600, 200, 500, 1);
            default: return Collections.emptyList();
        }
    }

    private static Collection<ResourceQuantity> orcBuildingCost(UnitType type) {
        switch (type) {
            case AltarOfStorms: return resources(900, 500, 0, 0);
            case BombardTower: return resources(1000, 300, 0, 0);
            case Dockyard: return resources(800, 450, 0, 0);
            case DragonRoost: return resources(1000, 400, 0, 0);
            case Encampment: return resources(700, 450, 0, 0);
            case Forge: return resources(800, 450, 100, 0);
            case Fortress: return resources(2500, 1200, 500, 0);
            case Metalworks: return resources(700, 400, 400, 0);
            case GoblinAlchemist: return resources(1000, 400, 0, 0);
            case GreatHall: return resources(1200, 800, 0, 0);
            case LookoutTower: return resources(550, 200, 0, 0);
            case OgreMound: return resources(1000, 300, 0, 0);
            case OilRefinery: return resources(800, 350, 200, 0);
            case OilRig: return resources(700, 450, 0, 0);
            case PigFarm: return resources(500, 250, 0, 0);
            case Stronghold: return resources(2000, 1000, 200, 0);
            case TempleOfTheDamned: return resources(1000, 200, 0, 0);
            case TrollLumberMill: return resources(600, 450, 0, 0);
            case WatchTower: return resources(500, 150, 0, 0);
            default: return Collections.emptyList();
        }
    }

    private static Collection<ResourceQuantity> orcCombatantCost(UnitType type) {
        switch (type) {
            case Catapult: return resources(900, 300, 0, 1);
            case DeathKnight: return resources(1200, 0, 0, 1);
            case Dragon: return resources(2500, 0, 0, 1);
            case Ferry: return resources(600, 200, 500, 1);
            case GiantTurtle: return resources(800, 150, 800, 1);
            case GoblinSappers: return resources(750, 250, 0, 1);
            case GoblinZeppelin: return resources(500, 100, 0, 1);
            case Grunt: return resources(600, 0, 0, 1);
            case Ogre: return resources(800, 100, 0, 1);
            case OgreJuggernaught: return resources(1000, 500, 1000, 1);
            case OgreMage: return resources(800, 100, 0, 1);
            case Peon: return resources(400, 0, 0, 1);
            case TrollAxethrower: return resources(500, 50, 0, 1);
            case TrollBerserker: return resources(500, 50, 0, 1);
            case TrollDestroyer: return resources(700, 350, 700, 1);
            case TrollTanker: return resources(400, 250, 0, 1);
            default: return Collections.emptyList();
        }
    }

    private static Collection<ResourceQuantity> resources(int gold, int wood, int oil, int food) {
        Set<ResourceQuantity> result = new HashSet<>();
        if (gold > 0) {
            result.add(new ResourceQuantity(Gold, gold));
        }
        if (wood > 0) {
            result.add(new ResourceQuantity(Wood, wood));
        }
        if (oil > 0) {
            result.add(new ResourceQuantity(Oil, oil));
        }
        if (food > 0) {
            result.add(new ResourceQuantity(Food, food));
        }
        return result;
    }

    public static Collection<ResourceQuantity> reservedResources(Item item) {
        return reservedResources(item.getType());
    }

    public static Collection<ResourceQuantity> reservedResources(Identifier type) {
        if (type instanceof UnitType) {
            UnitType unitType = (UnitType)type;
            if (unitType.isCombatant()) {
                return Sets.of(new ResourceQuantity(Food, 1));
            }
        }
        return Collections.emptyList();
    }
}