/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.data.product;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.time.Duration;
import com.evilbird.warcraft.data.resource.ResourceSet;
import com.evilbird.warcraft.object.unit.UnitArchetype;

/**
 * Implementors of this interface provide information related to the production
 * of a {@link Product}. Its cost for example.
 *
 * @author Blair Butterworth
 */
public interface Production
{
    /**
     * Returns the {@link ResourceSet resources} required to produce the
     * {@link Product}.
     */
    ResourceSet getCost();

    /**
     * Returns the {@link Duration time} required to produce an the
     * {@link Product}.
     */
    Duration getDuration();

    /**
     * Returns the {@link Identifier} of the facility required to produce the
     * {@link Product}.
     */
    UnitArchetype getProducer();
}
