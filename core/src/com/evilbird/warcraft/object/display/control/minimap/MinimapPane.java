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
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.control.Table;

import static com.badlogic.gdx.scenes.scene2d.Touchable.enabled;
import static com.evilbird.warcraft.object.display.HudControl.MinimapPane;

/**
 * Represents a user interface panel displayed near the top of the heads
 * up display control bar. Contains a zoomed out overview of the game world.
 *
 * @author Blair Butterworth
 */
public class MinimapPane extends Table
{
    private Minimap minimap;

    public MinimapPane(Skin skin) {
        setSkin(skin);
        setSize(176, 136);
        setTouchable(enabled);
        setIdentifier(MinimapPane);
        setType(MinimapPane);
    }

    @Override
    public void setSkin(Skin skin) {
        super.setSkin(skin);
        MinimapStyle style = skin.get("default", MinimapStyle.class);
        setBackground(style.frame);
    }

    public boolean initialized() {
        return minimap != null;
    }

    public void initialize(GameObjectContainer container) {
        minimap = new Minimap(container);
        Image image = new Image(minimap);

        removeObjects();
        Cell<Actor> cell = add(image);
        cell.width(176 - 48);
        cell.height(136 - 10);
        cell.pad(5, 24, 5, 24);
    }

    public void update(Vector2 position, Vector2 size) {
        minimap.update(position, size);
    }
}
