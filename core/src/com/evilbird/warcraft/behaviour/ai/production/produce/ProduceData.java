/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.production.produce;

import com.evilbird.warcraft.data.product.Product;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.building.Building;

/**
 * A bundle of attributes encapsulating the data required by unit and upgrade
 * production behaviour.
 *
 * @author Blair Butterworth
 */
public class ProduceData
{
    private Player player;
    private Product product;
    private Building producer;

    public ProduceData(Player player, Product product) {
        this.player = player;
        this.product = product;
    }

    public Player getPlayer() {
        return player;
    }

    public Building getProducer() {
        return producer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProducer(Building producer) {
        this.producer = producer;
    }
}
