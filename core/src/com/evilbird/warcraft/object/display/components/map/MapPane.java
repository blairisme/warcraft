/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.control.ControlUtils;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.control.Table;

import java.util.Objects;

import static com.badlogic.gdx.scenes.scene2d.Touchable.enabled;
import static com.evilbird.warcraft.object.display.components.UserInterfaceComponent.MinimapPane;

/**
 * Represents a user interface panel displayed near the top of the heads
 * up display control bar. Contains a zoomed out overview of the game world.
 *
 * @author Blair Butterworth
 */
public class MapPane extends Table
{
    private Map map;
    private Image image;
    private MapPaneStyle style;

    /**
     * Creates a new instance of this class given a {@link Skin} containing a
     * {@link MapPaneStyle} describing the visual presentation of the
     * MinimapPane.
     */
    public MapPane(Skin skin) {
        setSkin(skin);
        setTouchable(enabled);
        setIdentifier(MinimapPane);
        setType(MinimapPane);
    }

    /**
     * Returns the screen position of the map control contained in the MapPane.
     */
    public Vector2 getMapPosition() {
        Objects.requireNonNull(image, "MapPane uninitialized, missing call to initialize.");
        return ControlUtils.getScreenPosition(image);
    }

    /**
     * Returns the screen size of the map control contained in the MapPane.
     */
    public Vector2 getMapSize() {
        Objects.requireNonNull(image, "MapPane uninitialized, missing call to initialize.");
        return new Vector2(image.getWidth(), image.getHeight());
    }

    /**
     * Returns whether or the MapPane has been initialized or not.
     */
    public boolean initialized() {
        return map != null;
    }

    /**
     * Initializes the map, using the given {@link GameObjectContainer} to
     * populate the map with units and terrain.
     */
    public void initialize(GameObjectContainer container) {
        if (! initialized()) {
            map = new Map(container);
            image = new Image(map);
            image.setColor(style.colour);

            Cell<Actor> cell = add(image);
            cell.width(style.size.x);
            cell.height(style.size.y);
            cell.center();
        }
    }

    /**
     * Invalidates the region of the Minimap occupied by the given
     * {@link GameObject}.
     */
    public void invalidate(GameObject object) {
        if (map != null) {
            map.invalidate(object);
        }
    }

    /**
     * Sets the MapPanes {@link Skin} specifying its visual presentation. The
     * given {@code Skin} must contain a {@link MapPaneStyle}.
     */
    @Override
    public void setSkin(Skin skin) {
        super.setSkin(skin);
        style = skin.get("default", MapPaneStyle.class);
        if (style.background != null) {
            setBackground(style.background);
            setSize(style.background.getMinWidth(), style.background.getMinHeight());
        } else {
            setSize(style.size.x, style.size.y);
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (map != null) {
            map.update(delta);
        }
    }
}
