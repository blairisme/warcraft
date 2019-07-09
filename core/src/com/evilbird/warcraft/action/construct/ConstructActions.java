/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.warcraft.item.unit.UnitType;
import org.apache.commons.lang3.Validate;

import static com.evilbird.engine.common.collection.EnumUtils.getName;
import static com.evilbird.engine.common.collection.EnumUtils.isBetween;

/**
 * Defines options of specifying construction action varieties.
 *
 * @author Blair Butterworth
 */
public enum ConstructActions implements ActionIdentifier
{
    ConstructBarracks,
    ConstructBlacksmith,
    ConstructCannonTower,
    ConstructCastle,
    ConstructChurch,
    ConstructFarm,
    ConstructFoundry,
    ConstructGnomishInventor,
    ConstructGryphonAviary,
    ConstructGuardTower,
    ConstructKeep,
    ConstructLumberMill,
    ConstructMageTower,
    ConstructOilPlatform,
    ConstructRefinery,
    ConstructScoutTower,
    ConstructShipyard,
    ConstructStables,
    ConstructTownHall,

    ConstructAltarOfStorms,
    ConstructForge,
    ConstructEncampment,
    ConstructBombardTower,
    ConstructDockyard,
    ConstructDragonRoost,
    ConstructFortress,
    ConstructMetalworks,
    ConstructGoblinAlchemist,
    ConstructGreatHall,
    ConstructLookoutTower,
    ConstructOgreMound,
    ConstructOilRefinery,
    ConstructOilRig,
    ConstructPigFarm,
    ConstructStronghold,
    ConstructTempleOfTheDamned,
    ConstructTrollLumberMill,
    ConstructWatchTower,

    ConstructBarracksCancel,
    ConstructBlacksmithCancel,
    ConstructCannonTowerCancel,
    ConstructCastleCancel,
    ConstructChurchCancel,
    ConstructFarmCancel,
    ConstructFoundryCancel,
    ConstructGnomishInventorCancel,
    ConstructGryphonAviaryCancel,
    ConstructGuardTowerCancel,
    ConstructKeepCancel,
    ConstructLumberMillCancel,
    ConstructMageTowerCancel,
    ConstructOilPlatformCancel,
    ConstructRefineryCancel,
    ConstructScoutTowerCancel,
    ConstructShipyardCancel,
    ConstructStablesCancel,
    ConstructTownHallCancel,

    ConstructAltarOfStormsCancel,
    ConstructForgeCancel,
    ConstructEncampmentCancel,
    ConstructBombardTowerCancel,
    ConstructDockyardCancel,
    ConstructDragonRoostCancel,
    ConstructFortressCancel,
    ConstructMetalworksCancel,
    ConstructGoblinAlchemistCancel,
    ConstructGreatHallCancel,
    ConstructLookoutTowerCancel,
    ConstructOgreMoundCancel,
    ConstructOilRefineryCancel,
    ConstructOilRigCancel,
    ConstructPigFarmCancel,
    ConstructStrongholdCancel,
    ConstructTempleOfTheDamnedCancel,
    ConstructTrollLumberMillCancel,
    ConstructWatchTowerCancel;

    public boolean isCancel() {
        return isBetween(this, ConstructBarracksCancel, ConstructWatchTowerCancel);
    }

    public UnitType getProduct() {
        return UnitType.valueOf(getName(this, "Construct", "Cancel"));
    }

    public static ConstructActions forProduct(UnitType unitType) {
        Validate.isTrue(unitType.isBuilding());
        return ConstructActions.valueOf("Construct" + unitType.name());
    }
}
