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
import com.evilbird.engine.common.collection.EnumUtils;
import com.evilbird.warcraft.item.selector.SelectorType;
import org.apache.commons.lang3.Validate;

/**
 * Defines options of specifying selector varieties.
 *
 * @author Blair Butterworth
 */
public enum SelectorActions implements ActionIdentifier
{
    /* Human Building Selectors */
    ShowBarracksSelector,
    ShowBlacksmithSelector,
    ShowCannonTowerSelector,
    ShowCastleSelector,
    ShowChurchSelector,
    ShowFarmSelector,
    ShowFoundrySelector,
    ShowGnomishInventorSelector,
    ShowGryphonAviarySelector,
    ShowGuardTowerSelector,
    ShowKeepSelector,
    ShowLumberMillSelector,
    ShowMageTowerSelector,
    ShowOilPlatformSelector,
    ShowRefinerySelector,
    ShowScoutTowerSelector,
    ShowShipyardSelector,
    ShowStablesSelector,
    ShowTownHallSelector,

    /* Orc Building Selectors */
    ShowAltarOfStormsSelector,
    ShowForgeSelector,
    ShowEncampmentSelector,
    ShowBombardTowerSelector,
    ShowDockyardSelector,
    ShowDragonRoostSelector,
    ShowFortressSelector,
    ShowMetalworksSelector,
    ShowGoblinAlchemistSelector,
    ShowGreatHallSelector,
    ShowLookoutTowerSelector,
    ShowOgreMoundSelector,
    ShowOilRefinerySelector,
    ShowOilRigSelector,
    ShowPigFarmSelector,
    ShowStrongholdSelector,
    ShowTempleOfTheDamnedSelector,
    ShowTrollLumberMillSelector,
    ShowWatchTowerSelector,

    /* Target Selectors */
    ShowBlizzardSelector,
    ShowDemolitionSelector,
    ShowFireballSelector,
    ShowRuneTrapSelector,
    ShowWhirlwindSelector,

    /* Area Selector */
    ShowAreaSelector,
    ResizeAreaSelector,
    HideAreaSelector,

    /* Common Selectors Actions */
    MoveSelector,
    HideSelector;

    public boolean isShowAction() {
        return EnumUtils.isBetween(this, ShowBarracksSelector, ShowAreaSelector);
    }

    public SelectorType getSelector() {
        Validate.isTrue(isShowAction());
        return SelectorType.valueOf(EnumUtils.getName(this, "Show", ""));
    }

    public static SelectorActions forSelector(SelectorType selector) {
        return SelectorActions.valueOf("Show" + selector.name());
    }
}
