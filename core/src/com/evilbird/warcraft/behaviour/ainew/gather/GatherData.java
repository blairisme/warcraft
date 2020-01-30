/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.gather;

import com.evilbird.warcraft.data.resource.ResourceContainer;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

/**
 * A bundle of attributes encapsulating the data required by gathering
 * behaviour.
 *
 * @author Blair Butterworth
 */
public class GatherData
{
    private Player player;
    private Gatherer gatherer;
    private ResourceContainer resource;

    public GatherData(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Gatherer getGatherer() {
        return gatherer;
    }

    public ResourceContainer getResource() {
        return resource;
    }

    public void setGatherer(Gatherer gatherer) {
        this.gatherer = gatherer;
    }

    public void setResource(ResourceContainer resource) {
        this.resource = resource;
    }
}
