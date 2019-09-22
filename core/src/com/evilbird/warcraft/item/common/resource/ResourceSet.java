/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ResourceSet implements Iterable<ResourceQuantity>
{
    private Collection<ResourceQuantity> resources;

    public ResourceSet(Collection<ResourceQuantity> resources) {
        this.resources = resources;
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
