/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.menu;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.menu.ingame.IngameMenuType;
import com.evilbird.warcraft.object.display.components.actions.ActionPaneView;

/**
 * Defines options of specifying hud menu navigation action varieties.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("checkstyle:MethodParamPad")
public enum MenuActions implements ActionIdentifier
{
    ActionsMenu         (ActionPaneView.Actions),
    BuildSimpleMenu     (ActionPaneView.SimpleBuildings),
    BuildAdvancedMenu   (ActionPaneView.AdvancedBuildings),
    IngameMenu          (IngameMenuType.Root),
    FailureMenu         (IngameMenuType.Defeat),
    VictoryMenu         (IngameMenuType.Victory),
    MapOpen             (null),
    MapNavigate         (null);

    private Identifier menuIdentifier;

    MenuActions(Identifier location) {
        this.menuIdentifier = location;
    }

    public Identifier getMenuIdentifier() {
        return menuIdentifier;
    }
}
