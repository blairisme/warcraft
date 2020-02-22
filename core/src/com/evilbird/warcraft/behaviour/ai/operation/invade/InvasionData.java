/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.invade;

import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectComposite;
import com.evilbird.warcraft.object.data.player.Player;

import java.util.Collection;

/**
 * A bundle of attributes encapsulating the data required by invasion
 * behaviour.
 *
 * @author Blair Butterworth
 */
public class InvasionData
{
    private Player player;
    private Player enemy;
    private InvasionOrder order;
    private GameObject target;
    private Collection<GameObject> attackers;
    private GameObjectComposite world;

    public InvasionData(Player player, InvasionOrder order) {
        this.player = player;
        this.order = order;
    }

    public Collection<GameObject> getAttackers() {
        return attackers;
    }

    public Player getEnemy() {
        return enemy;
    }

    public InvasionOrder getOrder() {
        return order;
    }

    public Player getPlayer() {
        return player;
    }

    public GameObject getTarget() {
        return target;
    }

    public GameObjectComposite getWorld() {
        if (world == null) {
            world = player.getRoot();
        }
        return world;
    }

    public void setAttackers(Collection<GameObject> attackers) {
        this.attackers = attackers;
    }

    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }

    public void setTarget(GameObject target) {
        this.target = target;
    }
}
