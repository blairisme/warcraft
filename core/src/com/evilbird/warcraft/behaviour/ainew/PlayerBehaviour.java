/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.warcraft.object.data.player.Player;

/**
 * A common {@link BehaviorTree} implementation that modifies the game state
 * for a given artificial player.
 *
 * @author Blair Butterworth
 */
public abstract class PlayerBehaviour extends BehaviorTree<PlayerData>
{
    public PlayerBehaviour(Task<PlayerData> rootTask) {
        super(rootTask, new PlayerData());
    }

    public void setPlayer(Player player) {
        PlayerData data = getObject();
        data.setPlayer(player);
    }
}
