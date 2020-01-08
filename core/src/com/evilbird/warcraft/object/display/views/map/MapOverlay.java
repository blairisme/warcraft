/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.views.map;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.control.Table;
import com.evilbird.warcraft.object.display.UserInterfaceView;
import com.evilbird.warcraft.object.display.components.map.MapPane;
import com.evilbird.warcraft.object.display.components.map.MapPaneStyle;

/**
 * Represents a window overlaid on top of the game world showing a high level
 * overview of the game world.
 *
 * @author Blair Butterworth
 */
public class MapOverlay extends Table
{
    /**
     * Creates a new instance of this class given a {@link Skin} containing a
     * {@link MapPaneStyle} describing the visual presentation of the
     * MinimapPane.
     */
    public MapOverlay(Skin skin) {
        setSkin(skin);
        setCentered();
        setFillParent(true);
        setTouchable(Touchable.childrenOnly);
        setIdentifier(UserInterfaceView.MapOverlay);
        setType(UserInterfaceView.MapOverlay);

        MapPane pane = new MapPane(skin);
        //pane.setIdentifier(UserInterfaceView.MapOverlay);
        add(pane);
    }
}