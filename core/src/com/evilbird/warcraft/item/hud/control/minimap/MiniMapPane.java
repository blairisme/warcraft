/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.minimap;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.specialized.TableItem;
import com.evilbird.warcraft.item.hud.HudControl;

/**
 * Represents a user interface panel displayed near the top of the heads
 * up display control bar. Contains a zoomed out overview of the game world.
 *
 * @author Blair Butterworth
 */
public class MiniMapPane extends TableItem
{
    public MiniMapPane(Skin skin) {
        setSkin(skin);
        setSize(176, 136);
        setBackground("minimap-panel");
        setTouchable(Touchable.enabled);
        setIdentifier(HudControl.MinimapPane);
        setType(HudControl.MinimapPane);
    }
}
