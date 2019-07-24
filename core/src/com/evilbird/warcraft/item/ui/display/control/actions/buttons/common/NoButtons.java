/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.actions.buttons.common;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.ButtonController;

import java.util.Collections;
import java.util.List;

/**
 * Shown for items that do not have any associated buttons.
 *
 * @author Blair Butterworth
 */
public class NoButtons implements ButtonController
{
    private static NoButtons EMPTY_BUTTONS = new NoButtons();

    public static NoButtons emptyButtons() {
        return EMPTY_BUTTONS;
    }

    @Override
    public List<ActionButtonType> getButtons(Item item) {
        return Collections.emptyList();
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        return false;
    }
}
