/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.data.product.Product;
import com.evilbird.warcraft.object.unit.building.Building;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this {@link Event} are generated when a product is created.
 *
 * @author Blair Butterworth
 */
public class ProduceEvent implements Event
{
    private Product product;
    private Building producer;
    private ProduceStatus status;

    public ProduceEvent(Building producer, Product product, ProduceStatus status) {
        this.product = product;
        this.producer = producer;
        this.status = status;
    }

    public Building getBuilding() {
        return producer;
    }

    public Product getProduct() {
        return product;
    }

    public ProduceStatus getStatus() {
        return status;
    }

    @Override
    public GameObject getSubject() {
        return producer;
    }

    public boolean isTraining() {
        return status == ProduceStatus.Started;
    }

    public boolean isComplete() {
        return status == ProduceStatus.Complete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("product", product)
            .append("building", producer.getIdentifier())
            .append("status", status)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        ProduceEvent that = (ProduceEvent)obj;
        return new EqualsBuilder()
            .append(product, that.product)
            .append(producer, that.producer)
            .append(status, that.status)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(product)
            .append(producer)
            .append(status)
            .toHashCode();
    }
}
