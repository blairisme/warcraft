/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.actions.buttons;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.display.control.actions.ActionButton;
import com.evilbird.warcraft.object.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.control.actions.ActionPane;

import java.util.List;

/**
 * Implementors of this interface control the layout and enablement of
 * {@link ActionButton buttons} displayed in an {@link ActionPane}.
 *
 * @author Blair Butterworth
 */
public interface ButtonController
{
    /**
     * Returns the buttons that should be shown for the given {@link GameObject}.
     * Buttons will be displayed in the returned order.
     *
     * @param gameObject  the selected {@link GameObject}, whose attributes will be used
     *              to determine which buttons should be displayed.
     *
     * @return an ordered {@link List} of {@link ActionButtonType buttons}.
     */
    List<ActionButtonType> getButtons(GameObject gameObject);

    /**
     * Determines if buttons of the given type should be enabled or not.
     *
     * @param button    the {@link ActionButtonType type} of the button to test.
     * @param gameObject      the selected {@link GameObject}, whose attributes will be
     *                  used to determine if the button should be enabled.
     *
     * @return {@code true} if the button should be enabled.
     */
    boolean getEnabled(ActionButtonType button, GameObject gameObject);
}
