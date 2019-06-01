/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.desktop;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
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
        Input gdxInput = Mockito.mock(Input.class);
        input = new DesktopInput(gdxInput);
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
        input.pan(100, 200, 50, 50);
        input.pan(124, 124, 10, 10);

        List<UserInput> inputs = input.getInput();
        assertEquals(2, inputs.size());

        UserInput expected1 = new UserInput(UserInputType.Drag, new Vector2(100, 200), new Vector2(-50, 50), 1);
        UserInput expected2 = new UserInput(UserInputType.Drag, new Vector2(124, 124), new Vector2(-10, 10), 2);

        UserInput actual1 = inputs.get(0);
        UserInput actual2 = inputs.get(1);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);

        input.panStop(45, 56, 22, 33);
        input.pan(500, 500, 17, 17);

        List<UserInput> inputs2 = input.getInput();
        assertEquals(1, inputs2.size());

        UserInput expected3 = new UserInput(UserInputType.Drag, new Vector2(500, 500), new Vector2(-17, 17), 1);
        UserInput actual3 = inputs2.get(0);

        assertEquals(expected3, actual3);
    }

    @Test
    public void unsupportedTest() {
        assertFalse(input.touchDown(1, 2, 3, 4));
        assertFalse(input.longPress(1, 2));
        assertFalse(input.fling(1, 2, 3));
        assertFalse(input.pinch(Zero, Zero, Zero, Zero));
    }
}