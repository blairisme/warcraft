/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.device.DeviceInput;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;

import java.util.ArrayList;
import java.util.List;

/**
 * Reads user input to the desktop application, generating the appropriate
 * {@link UserInput} events.
 *
 * @author Blair Butterworth
 */
public class DesktopInput implements DeviceInput, GestureDetector.GestureListener
{
    private Input input;
    private List<UserInput> inputs;
    private int panCount;
    private int zoomCount;

    public DesktopInput() {
        this(Gdx.input);
    }

    public DesktopInput(Input input) {
        this.input = input;
        this.inputs = new ArrayList<>();
        this.panCount = 1;
        this.zoomCount = 1;
    }

    @Override
    public void install() {
        if (input == null) {
            input = Gdx.input;
        }
        GestureDetector gestureDetector = new GestureDetector(this);
        input.setInputProcessor(gestureDetector);
    }

    @Override
    public List<UserInput> readInput() {
        List<UserInput> result = new ArrayList<>(inputs);
        inputs.clear();
        return result;
    }

    private synchronized void pushInput(UserInput userInput) {
        inputs.add(userInput);
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        UserInput input = new UserInput(UserInputType.Action, new Vector2(x, y), 1);
        pushInput(input);
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        Vector2 position = new Vector2(x, y);
        Vector2 delta = new Vector2(deltaX * -1, deltaY);
        UserInput input = new UserInput(UserInputType.Drag, position, delta, panCount++);
        pushInput(input);
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        panCount = 1;
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        float scale = distance / initialDistance;
        UserInput input = new UserInput(UserInputType.Zoom, new Vector2(0, 0), new Vector2(scale, scale), zoomCount++);
        pushInput(input);
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {
        zoomCount = 1;
    }
}
