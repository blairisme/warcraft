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
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.input.GestureAnalyzer;
import com.evilbird.engine.common.input.GestureObserver;
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
public class DesktopInput implements DeviceInput, GestureObserver
{
    private Input input;
    private List<UserInput> inputs;
    private int panCount;
    private int zoomCount;
    private int depressedButton;
    private float depressedButtonX;
    private float depressedButtonY;

    public DesktopInput() {
        this(Gdx.input);
    }

    public DesktopInput(Input input) {
        this.input = input;
        this.inputs = new ArrayList<>();
        this.panCount = 1;
        this.zoomCount = 1;
        this.depressedButton = -1;
        this.depressedButtonX = -1;
        this.depressedButtonY = -1;
    }

    @Override
    public void startMonitoring() {
        Input controller = getInputController();
        controller.setInputProcessor(new GestureAnalyzer(this));
    }

    @Override
    public void stopMonitoring() {
        Input controller = getInputController();
        controller.setInputProcessor(null);
    }

    private Input getInputController() {
        if (input == null) {
            input = Gdx.input;
        }
        return input;
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
        depressedButton = button;
        depressedButtonX = x;
        depressedButtonY = y;
        if (button == 1) {
            UserInput input = new UserInput(UserInputType.SelectStart, new Vector2(x, y), 1);
            pushInput(input);
        }
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        depressedButton = -1;
        depressedButtonX = -1;
        depressedButtonY = -1;
        if (button == 1) {
            UserInput input = new UserInput(UserInputType.SelectStop, new Vector2(x, y), 1);
            pushInput(input);
        }
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
        if (depressedButton == 1) {
            Vector2 origin = new Vector2(depressedButtonX, depressedButtonY);
            Vector2 size = new Vector2(x, y);
            UserInput input = new UserInput(UserInputType.SelectResize, origin, size, panCount++);
            pushInput(input);
        } else {
            Vector2 position = new Vector2(x, y);
            Vector2 delta = new Vector2(deltaX * -1, deltaY);
            UserInput input = new UserInput(UserInputType.Drag, position, delta, panCount++);
            pushInput(input);
        }
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        panCount = 1;
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {
        zoomCount = 1;
    }

    @Override
    public boolean scrolled(int amount) {

        UserInput input = new UserInput(UserInputType.Zoom, new Vector2(0, 0), new Vector2(amount, amount), 1);
        pushInput(input);

        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        //float scale = distance / initialDistance;
        float scale  = initialDistance - distance;
        UserInput input = new UserInput(UserInputType.Zoom, new Vector2(0, 0), new Vector2(scale, scale), zoomCount++);
        pushInput(input);
        return true;
    }
}
