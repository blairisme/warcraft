/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.produce;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.UnitArchetype;
import com.evilbird.warcraft.object.unit.UnitType;

import java.util.Collection;

/**
 * A bundle of attributes encapsulating the data required by production
 * behaviour.
 *
 * @author Blair Butterworth
 */
public class ProductionData
{
    private Player player;
    private UnitType product;
    private UnitArchetype archetype;
    private ProductionOrder order;
    private ProductionManifest manifest;

    public ProductionData(Player player) {
        this.player = player;
        this.order = ProductionOrder.forPlayer(player);
    }

    public UnitType getProduct() {
        return product;
    }

    public UnitArchetype getProductArchetype() {
        return archetype;
    }

    public ProductionManifest getManifest() {
        return manifest;
    }

    public ProductionOrder getOrder() {
        return order;
    }

    public Player getPlayer() {
        return player;
    }

    public void setProduct(UnitType product) {
        this.product = product;
        this.archetype = product.getArchetype();
    }

    public void updateManifest(Collection<GameObject> objects) {
        manifest = new ProductionManifest(objects);
    }
}
