/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.common.production;

import com.evilbird.engine.game.GameService;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.data.upgrade.Upgrade;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;

import javax.inject.Inject;

/**
 * Defines the times required to create units and upgrades.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("DuplicateBranchesInSwitch")
public class ProductionTimes
{
    private WarcraftPreferences preferences;

    @Inject
    public ProductionTimes(WarcraftPreferences preferences) {
        this.preferences = preferences;
    }

    public ProductionTimes() {
        this((WarcraftPreferences)GameService.getInstance().getPreferences());
    }

    public float buildTime(Upgrade upgrade) {
        if (preferences.isBuildTimeCheatEnabled()) {
            return 0;
        }
        switch (upgrade) {
            case MeleeDamage1: return 20;
            case MeleeDamage2: return 20;
            case MeleeDefence1: return 20;
            case MeleeDefence2: return 20;
            case MeleeType1: return 20;
            case RangedDamage1: return 20;
            case RangedDamage2: return 20;
            case RangedAccuracy1: return 20;
            case RangedSight1: return 20;
            case RangedType1: return 20;
            case RangedWeapon1: return 20;
            case NavalDamage1: return 20;
            case NavalDamage2: return 20;
            case NavalDefence1: return 20;
            case NavalDefence2: return 20;
            case SiegeDamage1: return 20;
            case SiegeDamage2: return 20;
            default: throw new UnsupportedOperationException();
        }
    }

    public float buildTime(Unit unit) {
        return buildTime((UnitType)unit.getType());
    }

    public float buildTime(UnitType type) {
        if (preferences.isBuildTimeCheatEnabled()) {
            return 0;
        }
        if (type.isHuman()) {
            return humanBuildTime(type);
        }
        else if (type.isOrc()) {
            return orcBuildTime(type);
        }
        else if (type.isNeutral()) {
            return 0;
        }
        throw new UnsupportedOperationException();
    }

    private float humanBuildTime(UnitType type) {
        if (type.isBuilding()) {
            return humanBuildingBuildTime(type);
        }
        else if (type.isCombatant()) {
            return humanCombatantBuildTime(type);
        }
        throw new UnsupportedOperationException();
    }

    private float humanBuildingBuildTime(UnitType type) {
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
            default: throw new UnsupportedOperationException();
        }
    }

    private float humanCombatantBuildTime(UnitType type) {
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
            default: throw new UnsupportedOperationException();
        }
    }

    private float orcBuildTime(UnitType type) {
        if (type.isBuilding()) {
            return orcBuildingBuildTime(type);
        }
        else if (type.isCombatant()) {
            return orcCombatantBuildTime(type);
        }
        throw new UnsupportedOperationException();
    }

    private float orcBuildingBuildTime(UnitType type) {
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
            default: throw new UnsupportedOperationException();
        }
    }

    private float orcCombatantBuildTime(UnitType type) {
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
            default: throw new UnsupportedOperationException();
        }
    }
}
