/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.data.player;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.data.upgrade.Upgrade;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import java.util.EnumSet;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;

/**
 * Instances of this factory create {@link Player} objects.
 *
 * @author Blair Butterworth
 */
public class PlayerFactory implements GameFactory<Player>
{
    private WarcraftPreferences preferences;

    @Inject
    public PlayerFactory(WarcraftPreferences preferences) {
        this.preferences = preferences;
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

        if (preferences.isUpgradeCheatEnabled()) {
            player.setUpgrades(EnumSet.allOf(Upgrade.class), true);
        }
        return player;
    }
}
