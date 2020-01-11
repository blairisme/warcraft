/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.warcraft.object.unit.UnitType;
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

    public boolean isUpgrade() {
        return this == ConstructKeep || this == ConstructCastle
            || this == ConstructStronghold || this == ConstructFortress
            || this == ConstructGuardTower || this == ConstructCannonTower
            || this == ConstructLookoutTower || this == ConstructBombardTower;
    }

    public UnitType getProduct() {
        return UnitType.valueOf(getName(this, "Construct", "Cancel"));
    }

    public static ConstructActions forProduct(UnitType type) {
        Validate.isTrue(type.getArchetype().isBuilding());
        return ConstructActions.valueOf("Construct" + type.name());
    }
}
