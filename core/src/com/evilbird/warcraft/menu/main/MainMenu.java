/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.main;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.evilbird.engine.common.control.LabelButton;
import com.evilbird.engine.common.control.SelectListener;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.menu.Menu;

import javax.inject.Inject;

/**
 * Represents a user interface shown as the user enters the application, that
 * contains a options to start a new game, load and existing game or modify the
 * games settings.
 *
 * @author Blair Butterworth
 */
public class MainMenu extends Menu
{
    private Skin skin;
    private Table table;

    @Inject
    public MainMenu(DeviceDisplay display, Skin skin) {
        super(display);
        this.skin = skin;
        this.table = createTable();
        updateStyle();
    }

    public Skin getSkin() {
        return skin;
    }

    public void insertButton(String text) {
        LabelButton button = createButton(text);
        addButton(table, button);
    }

    public void insertButton(String text, SelectListener action) {
        LabelButton button = createButton(text, action);
        addButton(table, button);
    }

    private void updateStyle() {
        MainMenuStyle style = skin.get("default", MainMenuStyle.class);
        table.setBackground(style.background);
        setMusic(style.music);
    }

    private Table createTable() {
        Table table = new Table(skin);
        table.setFillParent(true);
        table.center();
        table.padTop(150f);

        Stage stage = getStage();
        stage.addActor(table);

        return table;
    }

    private LabelButton createButton(String text) {
        return createButton(text, null);
    }

    private LabelButton createButton(String text, SelectListener action) {
        LabelButton button = new LabelButton(text, skin);
        button.setDisabled(true);

        if (action != null) {
            button.setDisabled(false);
            button.addSelectListener(action);
        }
        return button;
    }

    private void addButton(Table table, LabelButton button) {
        Cell cell = table.add(button);
        cell.width(336);
        cell.height(28);
        cell.padBottom(8);
        cell.fill();
        table.row();
    }
}
