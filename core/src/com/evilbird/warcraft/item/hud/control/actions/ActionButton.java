/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.actions;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.specialized.TableItem;

import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.CancelButton;

/**
 * Instances of this user interface control represent a button that invokes an
 * action on a unit.
 *
 * @author Blair Butterworth
 */
public class ActionButton extends TableItem
{
    private ImageButton icon;

    public ActionButton(Skin skin) {
        initialize(skin);
        icon = addIcon();
    }

    public void setEnabled(boolean enabled) {
        icon.setDisabled(!enabled);
        setTouchable(enabled ? Touchable.enabled : Touchable.disabled);
    }

    public void setType(ActionButtonType buttonType) {
        super.setType(buttonType);
        icon.setStyle(getIconStyle(buttonType));
    }

    private void initialize(Skin skin) {
        setSkin(skin);
        setTouchable(Touchable.enabled);
        setBackground(getActionStyle().background);
    }

    private ImageButton addIcon() {
        ImageButton icon = new ImageButton(getIconStyle(CancelButton));
        Cell cell = add(icon);
        cell.pad(2);
        cell.row();
        return icon;
    }

    private ActionButtonStyle getActionStyle() {
        ActionButtonStyle style = getSkin().get("action-button", ActionButtonStyle.class);
        return style != null ? style : new ActionButtonStyle();
    }

    private ImageButtonStyle getIconStyle(ActionButtonType buttonType) {
        ActionButtonStyle actionStyle = getActionStyle();
        ImageButtonStyle buttonStyle = new ImageButtonStyle();

        if (actionStyle.icons != null) {
            buttonStyle.imageUp = actionStyle.icons.get(buttonType);
        }
        if (actionStyle.disabledIcons != null) {
            buttonStyle.imageDisabled = actionStyle.disabledIcons.get(buttonType);
        }
        return buttonStyle;
    }
}

