/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.produce.train;

import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;

/**
 * A bundle of attributes encapsulating the data required by unit training
 * behaviour.
 *
 * @author Blair Butterworth
 */
public class TrainData
{
    private Player player;
    private UnitType product;
    private Building producer;

    public TrainData(Player player, UnitType product) {
        this.player = player;
        this.product = product;
    }

    public Player getPlayer() {
        return player;
    }

    public Building getProducer() {
        return producer;
    }

    public UnitType getProduct() {
        return product;
    }

    public void setProducer(Building producer) {
        this.producer = producer;
    }
}
