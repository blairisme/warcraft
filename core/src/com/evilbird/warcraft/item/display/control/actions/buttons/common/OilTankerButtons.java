/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.actions.buttons.common;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.item.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.display.control.actions.buttons.ButtonController;

import java.util.List;

import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.GatherButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.MoveButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.OilPlatformButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.StopButton;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when an oil tanker is selected.
 *
 * @author Blair Butterworth
 */
public class OilTankerButtons implements ButtonController
{
    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        return asList(MoveButton, StopButton, OilPlatformButton, GatherButton);
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        return button == StopButton || button == OilPlatformButton;
    }
}
