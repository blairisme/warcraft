/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.components.actions;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.object.control.Table;
import com.evilbird.warcraft.object.unit.Unit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Instances of this user interface control represent a button that invokes an
 * action on a unit.
 *
 * @author Blair Butterworth
 */
public class ActionButton extends Table
{
    private transient ImageButton icon;

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

    public void setType(ActionButtonType buttonType, Unit unit) {
        super.setType(buttonType);
        icon.setStyle(getIconStyle(buttonType, unit));
    }

    private void initialize(Skin skin) {
        setSkin(skin);
        setTouchable(Touchable.enabled);
        setBackground(getActionStyle().background);
    }

    private ImageButton addIcon() {
        ImageButton icon = new ImageButton(getIconStyle(ActionButtonType.CancelButton));
        Cell cell = add(icon);
        cell.pad(2);
        cell.row();
        return icon;
    }

    private ActionButtonStyle getActionStyle() {
        ActionButtonStyle style = getSkin().get("action-button", ActionButtonStyle.class);
        return style != null ? style : new ActionButtonStyle();
    }

    private ImageButtonStyle getIconStyle(ActionButtonType button) {
        ActionButtonStyle actionStyle = getActionStyle();
        Drawable normal = actionStyle.icons != null ? actionStyle.icons.get(button) : null;
        Drawable disabled = actionStyle.disabledIcons != null ? actionStyle.disabledIcons.get(button) : null;
        return getButtonStyle(normal, disabled);
    }

    private ImageButtonStyle getIconStyle(ActionButtonType button, Unit unit) {
        ActionButtonStyle actionStyle = getActionStyle();
        Drawable normal = actionStyle.icons != null ? actionStyle.icons.get(button, unit) : null;
        Drawable disabled = actionStyle.disabledIcons != null ? actionStyle.disabledIcons.get(button, unit) : null;
        return getButtonStyle(normal, disabled);
    }

    private ImageButtonStyle getButtonStyle(Drawable normal, Drawable disabled) {
        ImageButtonStyle buttonStyle = new ImageButtonStyle();
        buttonStyle.imageUp = normal;
        buttonStyle.imageDisabled = disabled;
        return buttonStyle;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        ActionButton that = (ActionButton)obj;
        return new EqualsBuilder()
            .append(getType(), that.getType())
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(getType())
            .toHashCode();
    }
}

