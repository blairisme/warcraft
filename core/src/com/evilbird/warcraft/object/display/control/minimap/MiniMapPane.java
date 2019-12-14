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
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.control.Table;

import static com.evilbird.warcraft.object.display.HudControl.MinimapPane;

/**
 * Represents a user interface panel displayed near the top of the heads
 * up display control bar. Contains a zoomed out overview of the game world.
 *
 * @author Blair Butterworth
 */
public class MiniMapPane extends Table
{
    private Minimap minimap;

    public MiniMapPane(Skin skin) {
        setSkin(skin);
        setSize(176, 136);
        setBackground("minimap-panel");
        setTouchable(Touchable.enabled);
        setIdentifier(MinimapPane);
        setType(MinimapPane);
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
        cell.height(136 - 20);
        cell.pad(10, 24, 10, 24);
    }

    public void update(Vector2 position, Vector2 size) {
        minimap.update(position, size);
    }
}
