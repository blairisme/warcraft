/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.menu;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.evilbird.engine.item.specialized.Table;

import static com.evilbird.warcraft.item.display.HudControl.MenuPane;

/**
 * Represents a user interface panel displayed at the top of the heads
 * up display control bar. Contains a button that when clicked displays the
 * in-game menu.
 *
 * @author Blair Butterworth
 */
public class MenuPane extends Table
{
    public MenuPane(Skin skin) {
        initialize(skin);
        addControls(skin);
    }

    private void initialize(Skin skin) {
        setSkin(skin);
        setSize(176, 24);
        setCentered();
        setBackground("menu-panel");
        setTouchable(Touchable.enabled);
        setType(MenuPane);
        setIdentifier(MenuPane);
    }

    private void addControls(Skin skin) {
        TextButton button = new TextButton("Menu", skin, "button-thin-medium");
        add(button);
    }
}
