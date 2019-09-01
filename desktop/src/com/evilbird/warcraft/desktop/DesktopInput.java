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
import com.evilbird.engine.common.input.AbstractGestureObserver;
import com.evilbird.engine.common.input.GestureAnalyzer;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.device.DeviceInput;
import com.evilbird.engine.device.UserInput;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.Vector2.Zero;
import static com.evilbird.engine.device.UserInputType.Action;
import static com.evilbird.engine.device.UserInputType.Drag;
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
    private Input input;
    private List<UserInput> inputs;
    private int panCount;
    private int zoomCount;
    private int depressedButton;
    private float depressedButtonX;
    private float depressedButtonY;

    public DesktopInput(DeviceDisplay display) {
        this(display, Gdx.input);
    }

    public DesktopInput(DeviceDisplay display, Input input) {
        this.display = display;
        this.input = input;
        this.inputs = new ArrayList<>();
        this.panCount = 0;
        this.zoomCount = 0;
        this.depressedButton = 0;
        this.depressedButtonX = 0;
        this.depressedButtonY = 0;
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
    public boolean touchDown(float x, float y, int pointer, int button) {
        zoomCount = 0;
        depressedButton = button;
        depressedButtonX = x;
        depressedButtonY = y;

        if (button == SELECT_BUTTON) {
            addInput(new UserInput(PressDown, new Vector2(x, y), 1));
        }
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if (button == SELECT_BUTTON) {
            addInput(new UserInput(PressUp, new Vector2(x, y), 1));
        }
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        addInput(new UserInput(Action, new Vector2(x, y), 1));
        return true;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if (depressedButton == SELECT_BUTTON) {
            return panSelection(x, y);
        }
        return panCamera(x, y, deltaX, deltaY);
    }

    private boolean panCamera(float x, float y, float deltaX, float deltaY) {
        Vector2 position = new Vector2(x, y);
        Vector2 delta = new Vector2(deltaX * -1, deltaY);
        Vector2 distance = delta.scl(display.getScaleFactor());
        addInput(new UserInput(Drag, position, distance, ++panCount));
        return true;
    }

    private boolean panSelection(float x, float y) {
        Vector2 origin = new Vector2(depressedButtonX, depressedButtonY);
        Vector2 size = new Vector2(x, y);
        addInput(new UserInput(PressDrag, origin, size, ++panCount));
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        panCount = 0;
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        float zoom = ZOOM_SENSITIVITY * amount;
        Vector2 delta = new Vector2(zoom, zoom);
        Vector2 distance = delta.scl(1 / display.getScaleFactor());
        addInput(new UserInput(Zoom, Zero, distance, ++zoomCount));
        return false;
    }
}
