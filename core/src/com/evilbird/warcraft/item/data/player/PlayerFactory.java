/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.data.player;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;

/**
 * Instances of this factory create {@link Player} objects.
 *
 * @author Blair Butterworth
 */
public class PlayerFactory implements GameFactory<Player>
{
    private AssetManager assetManager;

    @Inject
    public PlayerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public PlayerFactory(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @Override
    public void load(GameContext context) {
    }

    @Override
    public void unload(GameContext context) {
    }

    @Override
    public Player get(Identifier type) {
        Validate.isInstanceOf(PlayerType.class, type);
        return get((PlayerType)type);
    }

    public Player get(PlayerType type) {
        Player player = new Player();
        player.setType(type);
        player.setIdentifier(objectIdentifier("Player", player));
        player.setPosition(0, 0);
        player.setSize(Float.MAX_VALUE, Float.MAX_VALUE);
        player.setVisible(true);
        return player;
    }
}
