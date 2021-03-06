/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.desktop;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.badlogic.gdx.math.Vector2.Zero;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Instances of this unit test validate the {@link DesktopInput} class.
 *
 * @author Blair Butterworth
 */
public class DesktopInputTest
{
    private DesktopInput input;

    @Before
    public void setup() {
        DeviceDisplay display = Mockito.mock(DeviceDisplay.class);
        Mockito.when(display.getScaleFactor()).thenReturn(1f);

        Input gdxInput = Mockito.mock(Input.class);
        input = new DesktopInput(display, gdxInput);
    }

    @Test
    public void tapTest() {
        input.tap(100, 200, 3, 1);

        List<UserInput> inputs = input.getInput();
        assertEquals(1, inputs.size());

        UserInput expected = new UserInput(UserInputType.Action, new Vector2(100, 200), 1);
        UserInput actual = inputs.get(0);

        assertEquals(expected, actual);
    }

    @Test
    public void panTest() {
        input.touchDown(50, 50, 1, 0);
        input.pan(100, 200, 50, 50);
        input.pan(124, 124, 10, 10);

        List<UserInput> inputs = input.getInput();
        assertEquals(3, inputs.size());

        UserInput expected1 = new UserInput(UserInputType.Drag, new Vector2(50, 50), new Vector2(0, 0), 1);
        UserInput expected2 = new UserInput(UserInputType.Drag, new Vector2(100, 200), new Vector2(-50, 50), 2);
        UserInput expected3 = new UserInput(UserInputType.Drag, new Vector2(124, 124), new Vector2(-10, 10), 3);

        UserInput actual1 = inputs.get(0);
        UserInput actual2 = inputs.get(1);
        UserInput actual3 = inputs.get(2);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);

        input.panStop(45, 56, 22, 33);
        input.touchDown(450, 450, 1, 0);
        input.pan(500, 500, 17, 17);

        List<UserInput> inputs2 = input.getInput();
        assertEquals(2, inputs2.size());

        UserInput expected4 = new UserInput(UserInputType.Drag, new Vector2(450, 450), new Vector2(0, 0), 1);
        UserInput expected5 = new UserInput(UserInputType.Drag, new Vector2(500, 500), new Vector2(-17, 17), 2);
        UserInput actual4 = inputs2.get(0);
        UserInput actual5 = inputs2.get(1);

        assertEquals(expected4, actual4);
        assertEquals(expected5, actual5);
    }

    @Test
    public void unsupportedTest() {
        assertFalse(input.touchDown(1, 2, 3, 4));
        assertFalse(input.longPress(1, 2));
        assertFalse(input.fling(1, 2, 3));
        assertFalse(input.pinch(Zero, Zero, Zero, Zero));
    }
}