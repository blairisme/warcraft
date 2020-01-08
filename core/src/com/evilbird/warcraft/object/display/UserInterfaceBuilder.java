/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display;

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
public class UserInterfaceBuilder
{
    private DeviceDisplay display;
    private GameObjectFactory objectFactory;

    @Inject
    public UserInterfaceBuilder(Device device, GameObjectFactory objectFactory) {
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
