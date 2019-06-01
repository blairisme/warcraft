/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.input.AbstractGestureObserver;
import com.evilbird.engine.common.input.GestureAnalyzer;
import com.evilbird.engine.device.DeviceInput;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.Vector2.Zero;
import static com.evilbird.engine.device.UserInputType.SelectStart;
import static com.evilbird.engine.device.UserInputType.SelectStop;
import static com.evilbird.engine.device.UserInputType.Zoom;

/**
 * Reads user input to the Android application, generating the appropriate
 * {@link UserInput} events.
 *
 * @author Blair Butterworth
 */
public class AndroidInput extends AbstractGestureObserver implements DeviceInput
{
    private static final float ZOOM_SENSITIVITY = 0.25f;
    private static final float ZOOM_MAX = 100;

    private Input input;
    private List<UserInput> inputs;
    private boolean longPress;
    private float depressedX;
    private float depressedY;
    private int panCount;
    private int zoomCount;
    private float zoomPrevious;

    public AndroidInput() {
        this(Gdx.input);
    }

    public AndroidInput(Input input) {
        this.input = input;
        this.inputs = new ArrayList<>();
        this.panCount = 0;
        this.zoomCount = 0;
        this.zoomPrevious = 0;
        this.longPress = false;
        this.depressedX = 0;
        this.depressedY = 0;
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
    public List<UserInput> getInput() {
        List<UserInput> result = new ArrayList<>(inputs);
        inputs.clear();
        return result;
    }

    private synchronized void addInput(UserInput userInput) {
        inputs.add(userInput);
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        UserInput input = new UserInput(UserInputType.Action, new Vector2(x, y), 1);
        addInput(input);
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        longPress = true;
        depressedX = x;
        depressedY = y;
        UserInput input = new UserInput(SelectStart, new Vector2(x, y), 1);
        addInput(input);
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if (longPress) {
            return panSelection(x, y);
        }
        return panCamera(x, y, deltaX, deltaY);
    }

    private boolean panCamera(float x, float y, float deltaX, float deltaY) {
        Vector2 position = new Vector2(x, y);
        Vector2 delta = new Vector2(deltaX * -1, deltaY);
        addInput(new UserInput(UserInputType.Drag, position, delta, ++panCount));
        return true;
    }

    private boolean panSelection(float x, float y) {
        Vector2 origin = new Vector2(depressedX, depressedY);
        Vector2 size = new Vector2(x, y);
        addInput(new UserInput(UserInputType.SelectResize, origin, size, ++panCount));
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        if (longPress) {
            return panSelectionStop(x, y);
        }
        return panCameraStop();
    }

    private boolean panCameraStop() {
        panCount = 0;
        return false;
    }

    private boolean panSelectionStop(float x, float y) {
        panCount = 0;
        longPress = false;
        addInput(new UserInput(SelectStop, new Vector2(x, y), 1));
        return true;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        float zoomDistance = zoomPrevious != 0 ? Math.abs(zoomPrevious - distance) : 1;
        float zoomScale = Math.min(zoomDistance / ZOOM_MAX, 1);
        float zoomAmount = ZOOM_SENSITIVITY * zoomScale;
        float zoom = distance <= zoomPrevious ? zoomAmount : zoomAmount * -1;
        zoomPrevious = distance;
        addInput(new UserInput(Zoom, Zero, new Vector2(zoom, zoom), ++zoomCount));
        return true;
    }

    @Override
    public void pinchStop() {
        zoomCount = 0;
        zoomPrevious = 0;
    }
}