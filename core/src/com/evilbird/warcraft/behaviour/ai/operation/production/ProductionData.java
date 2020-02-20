/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.production;

import com.evilbird.warcraft.data.product.Product;
import com.evilbird.warcraft.object.data.player.Player;

/**
 * A bundle of attributes encapsulating the data required by production
 * behaviour.
 *
 * @author Blair Butterworth
 */
public class ProductionData
{
    private Player player;
    private Product product;
    private ProductionOrder order;
    private ProductionManifest manifest;

    public ProductionData(Player player, ProductionOrder order) {
        this.player = player;
        this.order = order;
        this.manifest = new ProductionManifest();
    }

    public Product getProduct() {
        return product;
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

    public void setProduct(Product product) {
        this.product = product;
    }
}
