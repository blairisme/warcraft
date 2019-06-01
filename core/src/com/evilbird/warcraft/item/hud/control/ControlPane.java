/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.item.ItemBasic;
import com.evilbird.engine.item.specialized.TableItem;
import com.evilbird.warcraft.item.hud.HudControl;
import com.evilbird.warcraft.item.hud.control.actions.ActionPane;
import com.evilbird.warcraft.item.hud.control.menu.MenuPane;
import com.evilbird.warcraft.item.hud.control.minimap.MiniMapPane;
import com.evilbird.warcraft.item.hud.control.status.StatusPane;

/**
 * Instances of this class represent the user interface shown on the left of
 * the screen, containing the mini-map, unit stats and unit action buttons.
 *
 * @author Blair Butterworth
 */
public class ControlPane extends TableItem
{
    public ControlPane(Skin skin) {
        initialize(skin);
        addControls(skin);
    }

    private void initialize(Skin skin) {
        setSkin(skin);
        setFillParent(true);
        setType(HudControl.ControlPane);
        setTouchable(Touchable.childrenOnly);
    }

    private void addControls(Skin skin) {
        TableItem container = addContainer();
        ControlPaneStyle style = skin.get("default", ControlPaneStyle.class);
        if (style.showMenuButton) {
            addControl(container, new MenuPane(skin));
        }
        if (style.showMiniMap) {
            addControl(container, new MiniMapPane(skin));
        }
        if (style.showStatus) {
            addControl(container, new StatusPane(skin));
        }
        if (style.showActions) {
            addControl(container, new ActionPane(skin));
        }
    }

    private TableItem addContainer() {
        TableItem table = new TableItem();
        table.setAlignment(Alignment.Top);

        Cell cell = add(table);
        cell.align(Align.left);
        cell.expand();
        cell.row();

        return table;
    }

    private void addControl(TableItem table, ItemBasic item) {
        Cell cell = table.add(item);
        cell.row();
    }
}
