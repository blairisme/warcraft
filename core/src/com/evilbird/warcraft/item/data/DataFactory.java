/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.data;

import com.evilbird.engine.game.GameFactoryComposite;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.data.camera.CameraFactory;
import com.evilbird.warcraft.item.data.camera.CameraType;
import com.evilbird.warcraft.item.data.player.PlayerFactory;
import com.evilbird.warcraft.item.data.player.PlayerType;

import javax.inject.Inject;

/**
 * Instances of this factory create data {@link Item Items}, those with no
 * inherent visual representation that store user data.
 *
 * @author Blair Butterworth
 */
public class DataFactory extends GameFactoryComposite<Item>
{
    @Inject
    public DataFactory(CameraFactory cameraFactory, PlayerFactory playerFactory) {
        addProvider(CameraType.values(), cameraFactory);
        addProvider(PlayerType.values(), playerFactory);
    }
}
