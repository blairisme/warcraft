/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.selector;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.collection.EnumUtils;
import com.evilbird.warcraft.object.selector.SelectorType;
import org.apache.commons.lang3.Validate;

/**
 * Defines options of specifying selector actions.
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
    ShowDeathAndDecaySelector,
    ShowDemolitionSelector,
    ShowFireballSelector,
    ShowHolyVisionSelector,
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
