/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.selector;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.warcraft.item.ui.placement.PlaceholderType;
import org.apache.commons.lang3.Validate;

import static com.evilbird.engine.common.collection.EnumUtils.getName;

/**
 * Defines options of specifying selector varieties.
 *
 * @author Blair Butterworth
 */
public enum SelectorActions implements ActionIdentifier
{
    BarracksSelector,
    BlacksmithSelector,
    CannonTowerSelector,
    CastleSelector,
    ChurchSelector,
    FarmSelector,
    FoundrySelector,
    GnomishInventorSelector,
    GryphonAviarySelector,
    GuardTowerSelector,
    KeepSelector,
    LumberMillSelector,
    MageTowerSelector,
    OilPlatformSelector,
    RefinerySelector,
    ScoutTowerSelector,
    ShipyardSelector,
    StablesSelector,
    TownHallSelector,

    AltarOfStormsSelector,
    ForgeSelector,
    EncampmentSelector,
    BombardTowerSelector,
    DockyardSelector,
    DragonRoostSelector,
    FortressSelector,
    MetalworksSelector,
    GoblinAlchemistSelector,
    GreatHallSelector,
    LookoutTowerSelector,
    OgreMoundSelector,
    OilRefinerySelector,
    OilRigSelector,
    PigFarmSelector,
    StrongholdSelector,
    TempleOfTheDamnedSelector,
    TrollLumberMillSelector,
    WatchTowerSelector,
    
    SelectorMove,
    SelectorCancel;

    public boolean isAddAction() {
        return this != SelectorCancel && this != SelectorMove;
    }

    public PlaceholderType getPlaceholder() {
        Validate.isTrue(isAddAction());
        String name = name();
        String placeholder = name.replace("Selector", "Placeholder");
        return PlaceholderType.valueOf(placeholder);
    }

    public static SelectorActions forPlaceholder(PlaceholderType placeholderType) {
        String name = placeholderType.name();
        String selector = name.replace("Placeholder", "Selector");
        return SelectorActions.valueOf(selector);
    }
}
