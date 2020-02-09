/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.production;

import com.badlogic.gdx.ai.btree.branch.Sequence;

import javax.inject.Inject;

/**
 * A {@link Sequence} implementation representing the steps required by the
 * production behaviour. Namely to select a product for production, a nearby
 * builder or production facility, an unoccupied building location (in the case
 * of building production) and then to execute the production action.
 *
 * @author Blair Butterworth
 */
public class ProductionSequence extends Sequence<ProductionData>
{
    @Inject
    public ProductionSequence(
        ProductionInventory updateInventory,
        ProductionSelection selectProduct,
        ProductionTask produceProduct)
    {
        super(updateInventory, selectProduct, produceProduct);
    }
}
