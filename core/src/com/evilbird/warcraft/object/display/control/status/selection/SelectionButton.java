/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.status.selection;

import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.control.Button;
import com.evilbird.engine.object.control.Stack;
import com.evilbird.warcraft.object.display.control.common.UnitPane;
import com.evilbird.warcraft.object.unit.Unit;

import java.util.function.Supplier;

/**
 * Instances of this user interface control represent a selected item. The
 * control displays the icon and health of the selected item as well as
 * presenting an option allowing the selected item to be unselected.
 *
 * @author Blair Butterworth
 */
public class SelectionButton extends Stack implements Supplier<GameObject>
{
    private GameObject gameObject;
    private UnitPane icon;

    public SelectionButton(Skin skin) {
        setSize(56, 56);

        icon = new UnitPane(skin);
        icon.setType(SelectionButtonType.FocusButton);
        icon.setSize(54, 53);

        Button button = new Button(buttonStyle(skin));
        button.setType(SelectionButtonType.UnselectButton);
        button.setSize(18, 18);
        button.setPosition(40, 38);

        addObject(icon);
        addObject(button);
    }

    public GameObject get() {
        return gameObject;
    }

    public void set(GameObject gameObject) {
        this.gameObject = gameObject;
        this.icon.setItem((Unit) gameObject);
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
