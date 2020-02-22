/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.production;

import com.evilbird.warcraft.data.product.Product;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Specifies the order in which a players buildings should be constructed.
 *
 * @author Blair Butterworth
 */
public class ProductionOrder
{
    private List<Pair<Product, Integer>> order;

    @SafeVarargs
    public ProductionOrder(Pair<Product, Integer> ... entries) {
        this.order = Arrays.asList(entries);
    }

    public List<Pair<Product, Integer>> getSequence() {
        return Collections.unmodifiableList(order);
    }

    /**
     * Returns the next product that should be produced.
     */
    public Product getNextProduct(ProductionManifest manifest) {
        for (Pair<Product, Integer> entry: order) {
            if (! manifest.hasAtLeast(entry.getKey(), entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
