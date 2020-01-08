/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.data;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.data.camera.CameraFactory;
import com.evilbird.warcraft.object.data.camera.CameraType;
import com.evilbird.warcraft.object.data.player.PlayerFactory;
import com.evilbird.warcraft.object.data.player.PlayerType;

import javax.inject.Inject;

/**
 * Instances of this factory create data {@link GameObject Items}, those with no
 * inherent visual representation that store user data.
 *
 * @author Blair Butterworth
 */
public class DataFactory extends GameFactorySet<GameObject>
{
    @Inject
    public DataFactory(CameraFactory cameraFactory, PlayerFactory playerFactory) {
        addProvider(CameraType.values(), cameraFactory);
        addProvider(PlayerType.values(), playerFactory);
    }
}
