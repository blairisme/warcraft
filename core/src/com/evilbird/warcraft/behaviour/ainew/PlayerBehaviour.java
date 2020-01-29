/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.decorator.Repeat;
import com.evilbird.warcraft.object.data.player.Player;

import javax.inject.Inject;

/**
 * Modifies the game state on behalf of a given artificial player.
 *
 * @author Blair Butterworth
 */
public class PlayerBehaviour extends BehaviorTree<PlayerData>
{
    @Inject
    public PlayerBehaviour(PlayerTree sequence) {
        super(new Repeat<>(sequence), new PlayerData());
    }

    public void setPlayer(Player player) {
        PlayerData data = getObject();
        data.setPlayer(player);
    }
}
