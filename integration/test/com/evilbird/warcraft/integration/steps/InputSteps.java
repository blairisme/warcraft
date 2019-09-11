/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.integration.steps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameEngine;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.integration.device.IntegrationApplication;
import com.evilbird.warcraft.integration.device.IntegrationContext;
import com.evilbird.warcraft.integration.device.IntegrationInput;
import cucumber.api.java.en.Given;

import static com.evilbird.engine.item.utility.ItemPredicates.withType;
import static com.evilbird.warcraft.item.data.camera.CameraType.Camera;

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
        ItemRoot world = state.getWorld();
        Item camera = world.find(withType(Camera));
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
