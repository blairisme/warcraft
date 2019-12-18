/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display;

import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectFactory;

import javax.inject.Inject;

public class UserInterfaceLoader
{
    private DeviceDisplay display;
    private GameObjectFactory objectFactory;

    public UserInterfaceLoader() {
        GameService service = GameService.getInstance();
        this.display = service.getDevice().getDeviceDisplay();
        this.objectFactory = service.getItemFactory();
    }

    @Inject
    public UserInterfaceLoader(Device device, GameObjectFactory objectFactory) {
        this.display = device.getDeviceDisplay();
        this.objectFactory = objectFactory;
    }

    public GameObjectContainer get() {
        ScreenViewport viewport = new ScreenViewport();
        viewport.setUnitsPerPixel(display.getPixelUnits());

        GameObjectContainer result = new GameObjectContainer();
        result.setViewport(viewport);
        result.setIdentifier(UserInterfaceType.Hud);
        result.addObject(objectFactory.get(UserInterfaceType.Hud));
        return result;
    }
}
