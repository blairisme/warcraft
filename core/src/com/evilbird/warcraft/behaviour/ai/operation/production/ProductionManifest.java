/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.production;

import com.evilbird.engine.common.collection.Maps;
import com.evilbird.warcraft.data.product.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A record of the buildings and units owned by a player, listed by type and
 * accompanied by the quantity of each building type.
 *
 * @author Blair Butterworth
 */
public class ProductionManifest
{
    private Map<Product, Integer> records;

    /**
     * Constructs a new empty manifest.
     */
    public ProductionManifest() {
        records = new HashMap<>();
    }

    /**
     * Adds the given {@link Product} to the manifest, incrementing the number
     * of products of its type recorded in the manifest.
     */
    public void add(Product product) {
        int oldValue = Maps.getOrDefault(records, product, 0);
        int newValue = oldValue + 1;
        records.put(product, newValue);
    }

    public void addAll(Collection<? extends Product> products) {
        for (Product product: products) {
            add(product);
        }
    }

    /**
     * Determines if the manifest contains the given unit type and has at
     * least the given quantity of units of that type.
     */
    public boolean hasAtLeast(Product type, int quantity) {
        if (records.containsKey(type)) {
            return records.get(type) >= quantity;
        }
        return false;
    }

    public boolean isEmpty() {
        return records.isEmpty();
    }

    public void remove(Product product) {
        int oldValue = Maps.getOrDefault(records, product, 0);
        int newValue = Math.max(oldValue - 1, 0);
        records.put(product, newValue);
    }

    public void removeAll(Collection<Product> products) {
        for (Product product: products) {
            remove(product);
        }
    }
}
