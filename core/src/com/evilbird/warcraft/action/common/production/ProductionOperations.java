/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.common.production;

import com.evilbird.engine.common.time.Duration;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.data.product.Product;
import com.evilbird.warcraft.data.product.Production;
import com.evilbird.warcraft.data.resource.ResourceSet;

import static com.evilbird.engine.common.time.Duration.ZERO;
import static com.evilbird.warcraft.data.resource.ResourceSet.EmptyResourceSet;

/**
 * Contains common functions for determining the resource and time costs
 * required to produce a unit or upgrade.
 *
 * @author Blair Butterworth
 */
public class ProductionOperations
{
    /**
     * Disable construction of static utility class.
     */
    private ProductionOperations() {
    }

    /**
     * Returns the resource cost of producing the given product. If the user has
     * activated the free build cheat then an empty resource set will be
     * returned.
     */
    public static ResourceSet getProductionCost(Product product, WarcraftPreferences preferences) {
        if (!preferences.isBuildCostCheatEnabled()) {
            Production production = product.getProduction();
            return production.getCost();
        }
        return EmptyResourceSet;
    }

    /**
     * Returns the time required to produce a the given product. If the user
     * has activated the quick build cheat then a time of zero will be
     * returned.
     */
    public static Duration getProductionTime(Product product, WarcraftPreferences preferences) {
        if (!preferences.isBuildTimeCheatEnabled()) {
            Production production = product.getProduction();
            return production.getDuration();
        }
        return ZERO;
    }
}
