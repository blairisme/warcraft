/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.views.control;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.object.BasicGameObject;
import com.evilbird.engine.object.control.Table;
import com.evilbird.warcraft.object.display.components.UserInterfaceComponent;
import com.evilbird.warcraft.object.display.components.actions.ActionPane;
import com.evilbird.warcraft.object.display.components.map.MapPane;
import com.evilbird.warcraft.object.display.components.menu.MenuPane;
import com.evilbird.warcraft.object.display.components.status.StatusPane;

/**
 * Instances of this class represent the user interface shown on the left of
 * the screen, containing the mini-map, unit stats and unit action buttons.
 *
 * @author Blair Butterworth
 */
public class ControlPane extends Table
{
    public ControlPane(Skin skin) {
        initialize(skin);
        addControls(skin);
    }

    private void initialize(Skin skin) {
        setSkin(skin);
        setFillParent(true);
        setType(UserInterfaceComponent.ControlPane);
        setTouchable(Touchable.childrenOnly);
    }

    private void addControls(Skin skin) {
        Table container = addContainer();
        ControlPaneStyle style = skin.get("default", ControlPaneStyle.class);
        if (style.showMenuButton) {
            addControl(container, new MenuPane(skin));
        }
        if (style.showMiniMap) {
            addControl(container, new MapPane(skin));
        }
        if (style.showStatus) {
            addControl(container, new StatusPane(skin));
        }
        if (style.showActions) {
            addControl(container, new ActionPane(skin));
        }
    }

    private Table addContainer() {
        Table table = new Table();
        table.setAlignment(Alignment.Top);

        Cell<Actor> cell = add(table);
        cell.align(Align.left);
        cell.expand();
        cell.row();

        return table;
    }

    private void addControl(Table table, BasicGameObject item) {
        Cell<Actor> cell = table.add(item);
        cell.row();
    }
}