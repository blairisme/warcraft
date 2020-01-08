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
import java.util.Iterator;

/**
 * Represents a collection of {@link com.evilbird.warcraft.data.resource.ResourceQuantity ResourceQuantities}.
 *
 * @author Blair Butterworth
 */
public class ResourceSet implements Iterable<com.evilbird.warcraft.data.resource.ResourceQuantity>
{
    private Collection<com.evilbird.warcraft.data.resource.ResourceQuantity> resources;

    public ResourceSet(Collection<com.evilbird.warcraft.data.resource.ResourceQuantity> resources) {
        this.resources = resources;
    }

    @Override
    public Iterator<com.evilbird.warcraft.data.resource.ResourceQuantity> iterator() {
        return resources.iterator();
    }

    public ResourceSet negate() {
        Collection<com.evilbird.warcraft.data.resource.ResourceQuantity> negated = new ArrayList<>(resources.size());
        for (ResourceQuantity quantity: resources) {
            negated.add(quantity.negate());
        }
        return new ResourceSet(negated);
    }
}
