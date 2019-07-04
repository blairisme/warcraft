/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control.status.selection;

import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.ButtonItem;
import com.evilbird.engine.item.specialized.StackItem;
import com.evilbird.warcraft.item.ui.hud.control.common.UnitPane;
import com.evilbird.warcraft.item.unit.Unit;

import java.util.function.Supplier;

import static com.evilbird.warcraft.item.ui.hud.control.status.selection.SelectionButtonType.FocusButton;
import static com.evilbird.warcraft.item.ui.hud.control.status.selection.SelectionButtonType.UnselectButton;

/**
 * Instances of this user interface control represent a selected item. The
 * control displays the icon and health of the selected item as well as
 * presenting an option allowing the selected item to be unselected.
 *
 * @author Blair Butterworth
 */
public class SelectionButton extends StackItem implements Supplier<Item>
{
    private Item item;
    private UnitPane icon;

    public SelectionButton(Skin skin) {
        setSize(56, 56);

        icon = new UnitPane(skin);
        icon.setType(FocusButton);
        icon.setSize(54, 53);

        ButtonItem button = new ButtonItem(buttonStyle(skin));
        button.setType(UnselectButton);
        button.setSize(18, 18);
        button.setPosition(40, 38);

        addItem(icon);
        addItem(button);
    }

    public Item get() {
        return item;
    }

    public void set(Item item) {
        this.item = item;
        this.icon.setItem((Unit)item);
    }

    private ButtonStyle buttonStyle(Skin skin) {
        SelectionButtonStyle selectionStyle = skin.get("default", SelectionButtonStyle.class);
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = selectionStyle.closeButtonEnabled;
        buttonStyle.down = selectionStyle.closeButtonSelected;
        buttonStyle.disabled = selectionStyle.closeButtonDisabled;
        return buttonStyle;
    }
}
