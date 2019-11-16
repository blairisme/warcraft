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
import com.evilbird.warcraft.object.common.resource.ResourceQuantity;
import com.evilbird.warcraft.object.common.upgrade.Upgrade;
import com.evilbird.warcraft.object.unit.UnitType;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.evilbird.warcraft.object.common.resource.ResourceType.Food;
import static com.evilbird.warcraft.object.common.resource.ResourceType.Gold;
import static com.evilbird.warcraft.object.common.resource.ResourceType.Oil;
import static com.evilbird.warcraft.object.common.resource.ResourceType.Wood;

/**
 * Defines the resources required to create units and upgrades.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("DuplicateBranchesInSwitch")
public class ProductionCosts
{
    private WarcraftPreferences preferences;

    public ProductionCosts(){
        this((WarcraftPreferences)GameService.getInstance().getPreferences());
    }

    @Inject
    public ProductionCosts(WarcraftPreferences preferences) {
        this.preferences = preferences;
    }

    public Collection<ResourceQuantity> costOf(Upgrade upgrade) {
        if (preferences.isFreeBuildEnabled()) {
            return resources(0, 0, 0, 0);
        }
        if (upgrade.isAttributeUpgrade()) {
            return attributeUpgradeCost(upgrade);
        }
        if (upgrade.isFeatureUpgrade()) {
            return featureUpgradeCost(upgrade);
        }
        throw new UnsupportedOperationException();
    }

    private Collection<ResourceQuantity> attributeUpgradeCost(Upgrade upgrade) {
        switch (upgrade) {
            case MeleeDamage1: return resources(800, 0, 0, 0);
            case MeleeDamage2: return resources(2400, 0, 0, 0);
            case MeleeDefence1: return resources(300, 300, 0, 0);
            case MeleeDefence2: return resources(900, 500, 0, 0);
            case MeleeType1: return resources(1000, 0, 0, 0);
            case RangedDamage1: return resources(300, 300, 0, 0);
            case RangedDamage2: return resources(600, 0, 0, 0);
            case RangedAccuracy1: return resources(2500, 0, 0, 0);
            case RangedSight1: return resources(1500, 0, 0, 0);
            case RangedType1: return resources(1500, 0, 0, 0);
            case RangedWeapon1: return resources(2000, 0, 0, 0);
            case NavalDamage1: return resources(700, 100, 1000, 0);
            case NavalDamage2: return resources(2000, 250, 3000, 0);
            case NavalDefence1: return resources(500, 500, 0, 0);
            case NavalDefence2: return resources(1500, 900, 0, 0);
            case SiegeDamage1: return resources(1500, 0, 0, 0);
            case SiegeDamage2: return resources(4000, 0, 0, 0);
            default: throw new UnsupportedOperationException();
        }
    }

    private Collection<ResourceQuantity> featureUpgradeCost(Upgrade upgrade) {
        switch (upgrade) {
            case HasteUpgrade: return resources(500, 0, 0, 0);
            case RaiseTheDeadUpgrade: return resources(1500, 0, 0, 0);
            case WhirlwindUpgrade: return resources(1500, 0, 0, 0);
            case DeathAndDecayUpgrade: return resources(2000, 0, 0, 0);
            case UnholyArmourUpgrade: return resources(2500, 0, 0, 0);
            case BloodlustUpgrade: return resources(1000, 0, 0, 0);
            case RunesUpgrade: return resources(1000, 0, 0, 0);

            case BlizzardUpgrade: return resources(2000, 0, 0, 0);
            case ExorcismUpgrade: return resources(2000, 0, 0, 0);
            case FlameShieldUpgrade: return resources(1000, 0, 0, 0);
            case HealingUpgrade: return resources(1000, 0, 0, 0);
            case InvisibilityUpgrade: return resources(2500, 0, 0, 0);
            case PolymorphUpgrade: return resources(2000, 0, 0, 0);
            case SlowUpgrade: return resources(500, 0, 0, 0);
            default: throw new UnsupportedOperationException();
        }
    }

    public Collection<ResourceQuantity> costOf(UnitType type) {
        if (preferences.isFreeBuildEnabled()) {
            return resources(0, 0, 0, 0);
        }
        if (type.isHuman()) {
            return humanCosts(type);
        }
        else if (type.isOrc()) {
            return orcCosts(type);
        }
        else if (type.isNeutral()) {
            return Collections.emptyList();
        }
        throw new UnsupportedOperationException();
    }

    private Collection<ResourceQuantity> humanCosts(UnitType type) {
        if (type.isBuilding()) {
            return humanBuildingCost(type);
        }
        else if (type.isCombatant()) {
            return humanCombatantCost(type);
        }
        throw new UnsupportedOperationException();
    }

    private Collection<ResourceQuantity> humanBuildingCost(UnitType type) {
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
            default: throw new UnsupportedOperationException();
        }
    }

    private Collection<ResourceQuantity> humanCombatantCost(UnitType type) {
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
            default: throw new UnsupportedOperationException();
        }
    }

    private Collection<ResourceQuantity> orcCosts(UnitType type) {
        if (type.isBuilding()) {
            return orcBuildingCost(type);
        }
        else if (type.isCombatant()) {
            return orcCombatantCost(type);
        }
        throw new UnsupportedOperationException();
    }

    private Collection<ResourceQuantity> orcBuildingCost(UnitType type) {
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
            default: throw new UnsupportedOperationException();
        }
    }

    private Collection<ResourceQuantity> orcCombatantCost(UnitType type) {
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
            default: throw new UnsupportedOperationException();
        }
    }

    private Collection<ResourceQuantity> resources(int gold, int wood, int oil, int food) {
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
}