/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.display.views.control.ControlPaneFactory;
import com.evilbird.warcraft.object.display.views.map.MapOverlayFactory;
import com.evilbird.warcraft.object.display.views.resource.ResourceBarFactory;

import javax.inject.Inject;

import static com.evilbird.warcraft.object.display.UserInterfaceView.ControlBar;
import static com.evilbird.warcraft.object.display.UserInterfaceView.MapOverlay;
import static com.evilbird.warcraft.object.display.UserInterfaceView.ResourceBar;

/**
 * Creates the graphical user interface shown to the user while the game plays
 * out: the heads up display (HUD).
 *
 * @author Blair Butterworth
 */
public class UserInterfaceFactory extends GameFactorySet<GameObject>
{
    @Inject
    public UserInterfaceFactory(
        ControlPaneFactory controlPaneFactory,
        MapOverlayFactory mapOverlayFactory,
        ResourceBarFactory resourceBarFactory)
    {
        addProvider(ControlBar, controlPaneFactory);
        addProvider(ResourceBar, resourceBarFactory);
        addProvider(MapOverlay, mapOverlayFactory);
    }
}
