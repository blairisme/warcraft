/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.warcraft.item.ui.placement.PlaceholderType;
import org.apache.commons.lang3.Validate;

import static com.evilbird.engine.common.collection.EnumUtils.getName;

/**
 * Defines options of specifying placeholder action varieties.
 *
 * @author Blair Butterworth
 */
public enum PlaceholderActions implements ActionIdentifier
{
    AddBarracksPlaceholder,
    AddBlacksmithPlaceholder,
    AddCannonTowerPlaceholder,
    AddCastlePlaceholder,
    AddChurchPlaceholder,
    AddFarmPlaceholder,
    AddFoundryPlaceholder,
    AddGnomishInventorPlaceholder,
    AddGryphonAviaryPlaceholder,
    AddGuardTowerPlaceholder,
    AddKeepPlaceholder,
    AddLumberMillPlaceholder,
    AddMageTowerPlaceholder,
    AddOilPlatformPlaceholder,
    AddRefineryPlaceholder,
    AddScoutTowerPlaceholder,
    AddShipyardPlaceholder,
    AddStablesPlaceholder,
    AddTownHallPlaceholder,

    AddAltarOfStormsPlaceholder,
    AddForgePlaceholder,
    AddEncampmentPlaceholder,
    AddBombardTowerPlaceholder,
    AddDockyardPlaceholder,
    AddDragonRoostPlaceholder,
    AddFortressPlaceholder,
    AddMetalworksPlaceholder,
    AddGoblinAlchemistPlaceholder,
    AddGreatHallPlaceholder,
    AddLookoutTowerPlaceholder,
    AddOgreMoundPlaceholder,
    AddOilRefineryPlaceholder,
    AddOilRigPlaceholder,
    AddPigFarmPlaceholder,
    AddStrongholdPlaceholder,
    AddTempleOfTheDamnedPlaceholder,
    AddTrollLumberMillPlaceholder,
    AddWatchTowerPlaceholder,
    
    PlaceholderMove,
    PlaceholderCancel;

    public boolean isAddAction() {
        return this != PlaceholderCancel && this != PlaceholderMove;
    }

    public PlaceholderType getPlaceholder() {
        Validate.isTrue(isAddAction());
        return PlaceholderType.valueOf(getName(this, "Add", ""));
    }

    public static PlaceholderActions forPlaceholder(PlaceholderType placeholderType) {
        return PlaceholderActions.valueOf("Add" + placeholderType.name());
    }
}
