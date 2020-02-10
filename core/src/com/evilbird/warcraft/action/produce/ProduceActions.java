/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.data.product.Product;
import com.evilbird.warcraft.data.upgrade.Upgrade;
import com.evilbird.warcraft.object.unit.UnitType;

/**
 * Provides options of specifying production action varieties.
 *
 * @author Blair Butterworth
 */
public class ProduceActions
{
    /**
     * Disable construction of static helper class.
     */
    private ProduceActions() {
    }

    /**
     * Returns the identifier of the production action that produces the given
     * product.
     */
    public static Identifier forProduct(Product product) {
        if (product instanceof UnitType) {
            return ProduceUnitActions.forProduct((UnitType)product);
        }
        if (product instanceof Upgrade) {
            return ProduceUpgradeActions.forProduct((Upgrade)product);
        }
        throw new UnsupportedOperationException();
    }
}
