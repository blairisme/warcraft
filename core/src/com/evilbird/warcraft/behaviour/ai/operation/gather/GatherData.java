/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.gather;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.data.player.Player;

/**
 * A bundle of attributes encapsulating the data required by gathering
 * behaviour.
 *
 * @author Blair Butterworth
 */
public class GatherData
{
    private Player player;
    private GatherOrder order;
    private ResourceType resource;
    private GameObject gatherer;
    private GameObject location;

    /**
     * Creates a new instance of this class given the {@link Player} whose
     * units will be instructed to gather resources.
     */
    public GatherData(Player player, GatherOrder order) {
        this.player = player;
        this.order = order;
    }

    /**
     * Returns the unit that will be instructed to gather resources, if one has
     * been found. Returns {@code null} if not.
     */
    public GameObject getGatherer() {
        return gatherer;
    }

    /**
     * Returns the location where the gatherer will start to obtain resources.
     */
    public GameObject getLocation() {
        return location;
    }

    /**
     * Returns the {@link Player} whose units will be instructed to gather
     * resources.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns a {@link GatherOrder} specifying the order in which to gather
     * resources.
     */
    public GatherOrder getOrder() {
        return order;
    }

    /**
     * Returns the type of resource to gather next.
     */
    public ResourceType getResource() {
        return resource;
    }

    /**
     * Sets the game object that will be instructed to gather resources.
     */
    public void setGatherer(GameObject gatherer) {
        this.gatherer = gatherer;
    }

    /**
     * Sets the location at which to start gathering resources.
     */
    public void setLocation(GameObject location) {
        this.location = location;
    }

    /**
     * Sets the type of resource to gather next.
     */
    public void setResource(ResourceType resource) {
        this.resource = resource;
    }
}
