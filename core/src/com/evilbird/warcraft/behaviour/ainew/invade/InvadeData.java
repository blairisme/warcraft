/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.invade;

import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectComposite;
import com.evilbird.warcraft.object.data.player.Player;

import java.util.Collection;

/**
 * A bundle of attributes encapsulating the data required by invasion behaviour.
 *
 * @author Blair Butterworth
 */
public class InvadeData
{
    private Player player;
    private Player enemy;
    private GameObject enemyCommand;
    private GameObject playerCommand;
    private Collection<GameObject> attackers;

    public InvadeData(Player player) {
        this.player = player;
    }

    public Collection<GameObject> getAttackers() {
        return attackers;
    }

    public Player getEnemy() {
        return enemy;
    }

    public GameObject getEnemyCommand() {
        return enemyCommand;
    }

    public Player getPlayer() {
        return player;
    }

    public GameObject getPlayerCommand() {
        return playerCommand;
    }

    public GameObjectComposite getWorld() {
        return player.getRoot();
    }

    public void setAttackers(Collection<GameObject> attackers) {
        this.attackers = attackers;
    }

    public void setEnemy(GameObject enemy) {
        this.enemy = (Player)enemy;
    }

    public void setEnemyCommand(GameObject enemyCommand) {
        this.enemyCommand = enemyCommand;
    }

    public void setPlayerCommand(GameObject playerCommand) {
        this.playerCommand = playerCommand;
    }
}
