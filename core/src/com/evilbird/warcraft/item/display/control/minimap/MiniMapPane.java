/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.minimap;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.control.Table;
import com.evilbird.warcraft.item.display.HudControl;

/**
 * Represents a user interface panel displayed near the top of the heads
 * up display control bar. Contains a zoomed out overview of the game world.
 *
 * @author Blair Butterworth
 */
public class MiniMapPane extends Table
{
    public MiniMapPane(Skin skin) {
        setSkin(skin);
        setSize(176, 136);
        setBackground("minimap-panel");
        setTouchable(Touchable.enabled);
        setIdentifier(com.evilbird.warcraft.item.display.HudControl.MinimapPane);
        setType(HudControl.MinimapPane);
    }
}
