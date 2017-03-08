package com.evilbird.warcraft.item.data;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.data.camera.Camera;
import com.evilbird.warcraft.item.data.player.Player;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class DataProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public DataProvider(Provider<Camera> cameraProvider, Provider<Player> playerProvider)
    {
        addProvider(DataType.Camera, cameraProvider);
        addProvider(DataType.Player, playerProvider);
    }
}
