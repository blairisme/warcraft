/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.integration.steps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameEngine;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.integration.device.IntegrationApplication;
import com.evilbird.warcraft.integration.device.IntegrationContext;
import com.evilbird.warcraft.integration.device.IntegrationInput;
import cucumber.api.java.en.Given;

import static com.evilbird.engine.object.utility.GameObjectPredicates.withType;
import static com.evilbird.warcraft.object.data.camera.CameraType.Camera;

/**
 * @author Blair Butterworth
 */
public class InputSteps
{
    private IntegrationContext context;

    public InputSteps(IntegrationContext context) {
        this.context = context;
    }

    @Given("^the user scrolls the screen to x:(\\d+) y:(\\d+)$")
    public void scroll(int x, int y) {
        IntegrationApplication application = context.getApplication();
        application.update();

        GameEngine engine = context.getEngine();
        State state = engine.getState();
        GameObjectContainer world = state.getWorld();
        GameObject camera = world.find(withType(Camera));
        Vector2 screen = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Vector2 size = screen.scl(0.5f);
        Vector2 position = new Vector2(x + size.x, y + size.y);
        camera.setPosition(position);
    }

    @Given("^the user touches the screen at x:(\\d+) y:(\\d+)$")
    public void touch(int x, int y) {
        GameService service = context.getService();
        Device device = service.getDevice();
        IntegrationInput input = (IntegrationInput)device.getDeviceInput();
        input.tap(x, y);

        IntegrationApplication application = context.getApplication();
        application.updateCycle(5);
    }
}
