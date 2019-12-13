/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
