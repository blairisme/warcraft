/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.data.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Represents a collection of {@link ResourceQuantity ResourceQuantities}.
 *
 * @author Blair Butterworth
 */
public class ResourceSet implements Iterable<ResourceQuantity>
{
    private Collection<ResourceQuantity> resources;

    public ResourceSet(Collection<ResourceQuantity> resources) {
        this.resources = Collections.unmodifiableCollection(resources);
    }

    @Override
    public Iterator<ResourceQuantity> iterator() {
        return resources.iterator();
    }

    public ResourceSet negate() {
        Collection<ResourceQuantity> negated = new ArrayList<>(resources.size());
        for (ResourceQuantity quantity: resources) {
            negated.add(quantity.negate());
        }
        return new ResourceSet(negated);
    }
}
