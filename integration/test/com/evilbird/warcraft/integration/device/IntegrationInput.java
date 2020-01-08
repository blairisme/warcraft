/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
