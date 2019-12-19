/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.GameObjectGroup;

import javax.inject.Inject;

import static com.badlogic.gdx.scenes.scene2d.Touchable.childrenOnly;
import static com.evilbird.engine.object.GameObjectContainerType.Hud;
import static com.evilbird.warcraft.object.display.UserInterfaceView.ControlBar;
import static com.evilbird.warcraft.object.display.UserInterfaceView.ResourceBar;

/**
 * Creates a new in-game user interface instance.
 *
 * @author Blair Butterworth
 */
public class WarcraftInterfaceLoader
{
    private DeviceDisplay display;
    private GameObjectFactory objectFactory;

    @Inject
    public WarcraftInterfaceLoader(Device device, GameObjectFactory objectFactory) {
        this.display = device.getDeviceDisplay();
        this.objectFactory = objectFactory;
    }

    public GameObjectContainer get() {
        ScreenViewport viewport = new ScreenViewport();
        viewport.setUnitsPerPixel(display.getPixelUnits());

        GameObjectContainer result = new GameObjectContainer();
        result.setViewport(viewport);
        result.setIdentifier(Hud);

        GameObjectGroup group = result.getBaseGroup();
        group.setFillParent(true);
        group.setTouchable(childrenOnly);

        result.addObject(objectFactory.get(ResourceBar));
        result.addObject(objectFactory.get(ControlBar));
//        result.addObject(objectFactory.get(MapOverlay));

        return result;
    }
}
