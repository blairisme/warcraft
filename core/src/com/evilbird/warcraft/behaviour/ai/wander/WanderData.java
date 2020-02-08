/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.wander;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.data.player.Player;

/**
 * A bundle of attributes encapsulating the data required by wander behaviour.
 *
 * @author Blair Butterworth
 */
public class WanderData
{
    private Player player;
    private MovableObject subject;
    private Vector2 destination;

    public WanderData(Player player) {
        this.player = player;
    }

    public Vector2 getDestination() {
        return destination;
    }

    public Player getPlayer() {
        return player;
    }

    public MovableObject getSubject() {
        return subject;
    }

    public void setDestination(Vector2 destination) {
        this.destination = destination;
    }

    public void setSubject(MovableObject subject) {
        this.subject = subject;
    }
}
