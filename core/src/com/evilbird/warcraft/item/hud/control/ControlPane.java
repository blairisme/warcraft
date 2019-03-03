/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.control.GridPane;
import com.evilbird.warcraft.item.hud.HudControl;
import com.evilbird.warcraft.item.hud.control.actions.ActionPaneProvider;
import com.evilbird.warcraft.item.hud.control.menu.MenuPaneFactory;
import com.evilbird.warcraft.item.hud.control.minimap.MinimapPaneProvider;
import com.evilbird.warcraft.item.hud.control.status.StatePane;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Instances of this class represent the user interface shown on the left of
 * the screen, containing the mini-map, unit stats and unit action buttons.
 *
 * @author Blair Butterworth
 */
public class ControlPane extends GridPane
{
    @Inject
    public ControlPane(
        ActionPaneProvider actionPaneProvider,
        MinimapPaneProvider minimapPaneProvider,
        Provider<StatePane> statePaneProvider,
        MenuPaneFactory menuPaneFactory)
    {
        super(1, 4);
        setSize(176, 488); //TODO - Automagically scale
        setPosition(0, 140); //TODO - Replace with Align left center
        setCellPadding(0);
        setCellSpacing(0);
        setCell(menuPaneFactory.get(), 0, 0);
        setCell(minimapPaneProvider.get(), 0, 1);
        setCell(statePaneProvider.get(), 0, 2);
        setCell(actionPaneProvider.get(), 0, 3);
        setType(HudControl.ControlPane);
        setTouchable(Touchable.enabled);
    }
}
