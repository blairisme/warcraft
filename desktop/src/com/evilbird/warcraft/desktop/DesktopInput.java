/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.input.AbstractGestureObserver;
import com.evilbird.engine.common.input.GestureAnalyzer;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.device.DeviceInput;
import com.evilbird.engine.device.UserInput;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.Vector2.Zero;
import static com.evilbird.engine.device.DeviceInputConstants.InputConsumed;
import static com.evilbird.engine.device.DeviceInputConstants.InputNotConsumed;
import static com.evilbird.engine.device.UserInputType.Action;
import static com.evilbird.engine.device.UserInputType.Drag;
import static com.evilbird.engine.device.UserInputType.Key;
import static com.evilbird.engine.device.UserInputType.PressDown;
import static com.evilbird.engine.device.UserInputType.PressDrag;
import static com.evilbird.engine.device.UserInputType.PressUp;
import static com.evilbird.engine.device.UserInputType.Zoom;

/**
 * Reads user input to the desktop application, generating the appropriate
 * {@link UserInput} events.
 *
 * @author Blair Butterworth
 */
public class DesktopInput extends AbstractGestureObserver implements DeviceInput
{
    private static final float ZOOM_SENSITIVITY = 0.10f;
    private static final int SELECT_BUTTON = 1;

    private DeviceDisplay display;
    private float displayScale;
    private float displayScaleFactor;

    private Input input;
    private List<UserInput> inputs;

    private int panCount;
    private int zoomCount;

    private int touchButton;
    private Vector2 touchPosition;


    public DesktopInput(DeviceDisplay display) {
        this(display, Gdx.input);
    }

    public DesktopInput(DeviceDisplay display, Input input) {
        this.display = display;
        this.displayScale = 1;
        this.displayScaleFactor = 1;

        this.input = input;
        this.inputs = new ArrayList<>();

        this.panCount = 0;
        this.zoomCount = 0;

        this.touchButton = 0;
        this.touchPosition = Zero;
    }

    @Override
    public void startMonitoring() {
        Input controller = getInputController();
        controller.setInputProcessor(new GestureAnalyzer(this));
        initializeDisplayScale();
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

    private void initializeDisplayScale() {
        this.displayScale = display.getScaleFactor();
        this.displayScaleFactor = 1 / displayScale;
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
    public boolean keyUp(int keycode) {
        addInput(new UserInput(Key, keycode));
        return InputNotConsumed;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        resetZoomGesture();
        updateButtonState(x, y, button);
        addSelectionGesture(x, y, button);
        return InputNotConsumed;
    }

    private void updateButtonState(float x, float y, int button) {
        touchButton = button;
        touchPosition = new Vector2(x, y);
    }

    private void addSelectionGesture(float x, float y, int button) {
        if (button == SELECT_BUTTON) {
            addInput(new UserInput(PressDown, new Vector2(x, y), 1));
        }
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if (button == SELECT_BUTTON) {
            addInput(new UserInput(PressUp, new Vector2(x, y), 1));
        }
        return InputNotConsumed;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        addInput(new UserInput(Action, new Vector2(x, y), 1));
        return InputConsumed;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if (touchButton == SELECT_BUTTON) {
            return dragSelectGesture(x, y);
        } else {
            return dragCameraGesture(x, y, deltaX, deltaY);
        }
    }

    private boolean dragCameraGesture(float x, float y, float deltaX, float deltaY) {
        addDragGestureOrigin();
        addDragGestureCurrent(x, y, deltaX, deltaY);
        return InputConsumed;
    }

    private void addDragGestureOrigin() {
        if (panCount == 0) {
            addInput(new UserInput(Drag, touchPosition, Zero, ++panCount));
        }
    }

    private void addDragGestureCurrent(float x, float y, float deltaX, float deltaY) {
        Vector2 position = new Vector2(x, y);
        Vector2 delta = new Vector2(deltaX * -1, deltaY);
        Vector2 distance = delta.scl(displayScale);
        addInput(new UserInput(Drag, position, distance, ++panCount));
    }

    private boolean dragSelectGesture(float x, float y) {
        Vector2 size = new Vector2(x, y);
        addInput(new UserInput(PressDrag, touchPosition, size, ++panCount));
        return InputConsumed;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        resetPan();
        return InputNotConsumed;
    }

    @Override
    public boolean scrolled(int amount) {
        float zoom = ZOOM_SENSITIVITY * amount;
        float scaled = zoom * displayScaleFactor;
        Vector2 delta = new Vector2(scaled, scaled);
        addInput(new UserInput(Zoom, Zero, delta, ++zoomCount));
        return InputNotConsumed;
    }

    private void resetPan() {
        panCount = 0;
    }

    private void resetZoomGesture() {
        zoomCount = 0;
    }
}
