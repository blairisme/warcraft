/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
