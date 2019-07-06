/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control.actions;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.specialized.TableItem;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.item.unit.UnitFaction.Human;

/**
 * Instances of this user interface control represent a button that invokes an
 * action on a unit.
 *
 * @author Blair Butterworth
 */
public class ActionButton extends TableItem
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
            buttonStyle.imageUp = actionStyle.icons.get(buttonType, Human);
        }
        if (actionStyle.disabledIcons != null) {
            buttonStyle.imageDisabled = actionStyle.disabledIcons.get(buttonType, Human);
        }
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

