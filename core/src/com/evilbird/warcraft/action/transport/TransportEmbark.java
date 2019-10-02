/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.transport;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.warcraft.action.common.exclusion.ItemExclusion;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;

/**
 * Represents an {@link Action} that facilitates an individual item entering a
 * vessel.
 *
 * @author Blair Butterworth
 */
public class TransportEmbark extends BasicAction
{
    private transient ItemExclusion exclusion;

    @Inject
    public TransportEmbark(ItemExclusion exclusion) {
        this.exclusion = exclusion;
    }

    @Override
    public boolean act(float delta) {
        Unit embarkee = (Unit)getItem();
        exclusion.disable(embarkee);

        Unit vessel = (Unit)getTarget();
        vessel.addAssociatedItem(embarkee);

        return ActionComplete;
    }
}
