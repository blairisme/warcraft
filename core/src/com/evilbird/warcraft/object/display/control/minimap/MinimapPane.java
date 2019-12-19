/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.minimap;

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
import static com.evilbird.warcraft.object.display.UserInterfaceControl.MinimapPane;

/**
 * Represents a user interface panel displayed near the top of the heads
 * up display control bar. Contains a zoomed out overview of the game world.
 *
 * @author Blair Butterworth
 */
public class MinimapPane extends Table
{
    private static transient final float WIDTH = 176.0f;
    private static transient final float HEIGHT = 136.0f;
    private static transient final float HORIZONTAL_PADDING = 24.0f;
    private static transient final float VERTICAL_PADDING = 2.0f;

    private Minimap minimap;
    private Image image;

    /**
     * Creates a new instance of this class given a {@link Skin} containing a
     * {@link MinimapStyle} describing the visual presentation of the
     * MinimapPane.
     */
    public MinimapPane(Skin skin) {
        setSkin(skin);
        setSize(WIDTH, HEIGHT);
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
        return minimap != null;
    }

    /**
     * Initializes the map, using the given {@link GameObjectContainer} to
     * populate the map with units and terrain.
     */
    public void initialize(GameObjectContainer container) {
        minimap = new Minimap(container);
        image = new Image(minimap);

        removeObjects();
        Cell<Actor> cell = add(image);
        cell.width(WIDTH - (HORIZONTAL_PADDING * 2));
        cell.height(HEIGHT - (VERTICAL_PADDING * 2));
        cell.pad(VERTICAL_PADDING, HORIZONTAL_PADDING, 2, HORIZONTAL_PADDING);
    }

    /**
     * Invalidates the region of the Minimap occupied by the given
     * {@link GameObject}.
     */
    public void invalidate(GameObject object) {
        if (minimap != null) {
            minimap.invalidate(object);
        }
    }

    /**
     * Sets the MapPanes {@link Skin} specifying its visual presentation. The
     * given {@code Skin} must contain a {@link MinimapStyle}.
     */
    @Override
    public void setSkin(Skin skin) {
        super.setSkin(skin);
        MinimapStyle style = skin.get("default", MinimapStyle.class);
        setBackground(style.frame);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (minimap != null) {
            minimap.update(delta);
        }
    }
}
