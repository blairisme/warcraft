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
import com.evilbird.warcraft.data.resource.ResourceSet;
import com.evilbird.warcraft.data.upgrade.Upgrade;
import com.evilbird.warcraft.data.upgrade.UpgradeProduction;
import com.evilbird.warcraft.object.unit.UnitProduction;
import com.evilbird.warcraft.object.unit.UnitType;

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
     * Returns the resource cost of producing the given unit. If the user has
     * activated the free build cheat then an empty resource set will be
     * returned.
     */
    public static ResourceSet getProductionCost(UnitType product, WarcraftPreferences preferences) {
        if (!preferences.isBuildCostCheatEnabled()) {
            UnitProduction production = UnitProduction.forProduct(product);
            return production.getCost();
        }
        return EmptyResourceSet;
    }

    /**
     * Returns the resource cost of producing the given upgrade. If the user
     * has activated the free build cheat then an empty resource set will be
     * returned.
     */
    public static ResourceSet getProductionCost(Upgrade product, WarcraftPreferences preferences) {
        if (!preferences.isBuildCostCheatEnabled()) {
            UpgradeProduction production = UpgradeProduction.forProduct(product);
            return production.getCost();
        }
        return EmptyResourceSet;
    }

    /**
     * Returns the time required to produce a the given unit. If the user
     * has activated the quick build cheat then a time of zero will be
     * returned.
     */
    public static Duration getProductionTime(UnitType product, WarcraftPreferences preferences) {
        if (!preferences.isBuildTimeCheatEnabled()) {
            UnitProduction production = UnitProduction.forProduct(product);
            return production.getDuration();
        }
        return ZERO;
    }

    /**
     * Returns the time required to produce a the given upgrade. If the user
     * has activated the quick build cheat then a time of zero will be
     * returned.
     */
    public static Duration getProductionTime(Upgrade product, WarcraftPreferences preferences) {
        if (!preferences.isBuildTimeCheatEnabled()) {
            UpgradeProduction production = UpgradeProduction.forProduct(product);
            return production.getDuration();
        }
        return ZERO;
    }
}
