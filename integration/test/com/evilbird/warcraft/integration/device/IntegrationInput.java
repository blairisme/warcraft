/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.integration.device;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.device.DeviceInput;
import com.evilbird.engine.device.UserInput;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.engine.device.UserInputType.Action;

/**
 * Reads user input to the desktop application, generating the appropriate
 * {@link UserInput} events.
 *
 * @author Blair Butterworth
 */
public class IntegrationInput implements DeviceInput
{
    private List<UserInput> inputs;

    public IntegrationInput() {
        this.inputs = new ArrayList<>();
    }

    @Override
    public void startMonitoring() {
    }

    @Override
    public void stopMonitoring() {
    }

    @Override
    public List<UserInput> getInput() {
        List<UserInput> result = new ArrayList<>(inputs);
        inputs.clear();
        return result;
    }

    public synchronized void addInput(UserInput userInput) {
        inputs.add(userInput);
    }

    public void tap(float x, float y) {
        tap(new Vector2(x, y));
    }

    public void tap(Vector2 position) {
        addInput(new UserInput(Action, position, 1));
    }
}
