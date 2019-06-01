/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.menu;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.item.hud.control.actions.ActionPaneView;
import com.evilbird.warcraft.menu.ingame.IngameMenuType;

/**
 * Defines options of specifying hud menu navigation action varieties.
 *
 * @author Blair Butterworth
 */
public enum MenuActions implements ActionIdentifier
{
    ActionsMenu(ActionPaneView.Actions),
    BuildSimpleMenu(ActionPaneView.SimpleBuildings),
    BuildAdvancedMenu(ActionPaneView.AdvancedBuildings),
    IngameMenu(IngameMenuType.Root),
    FailureMenu(IngameMenuType.Defeat),
    VictoryMenu(IngameMenuType.Victory);

    private Identifier menuIdentifier;

    MenuActions(Identifier location) {
        this.menuIdentifier = location;
    }

    public Identifier getMenuIdentifier() {
        return menuIdentifier;
    }
}
