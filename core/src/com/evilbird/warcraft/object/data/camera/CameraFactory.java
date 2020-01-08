/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.data.camera;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Camera} objects.
 *
 * @author Blair Butterworth
 */
public class CameraFactory implements GameFactory<Camera>
{
    private Device device;

    @Inject
    public CameraFactory(Device device) {
        this.device = device;
    }

    @Override
    public Camera get(Identifier identifier) {
        return new Camera(device);
    }

    @Override
    public void load(GameContext context) {
    }

    @Override
    public void unload(GameContext context) {
    }
}
