/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.navigate;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.item.hud.control.actions.ActionPaneLayout;

/**
 * Defines options of specifying hud menu navigation action varieties.
 *
 * @author Blair Butterworth
 */
public enum NavigateActions implements ActionIdentifier
{
    ActionsMenu         (ActionPaneLayout.Actions),
    BuildSimpleMenu     (ActionPaneLayout.SimpleBuildings),
    BuildAdvancedMenu   (ActionPaneLayout.AdvancedBuildings);

    private Identifier location;

    NavigateActions(ActionPaneLayout location) {
        this.location = location;
    }

    public Identifier getNavigateLocation() {
        return location;
    }
}
