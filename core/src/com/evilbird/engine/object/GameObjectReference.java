/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.object;

import com.evilbird.engine.common.lang.Identifier;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.function.Supplier;

import static com.evilbird.engine.object.utility.GameObjectPredicates.itemWithId;

/**
 * Instances of this class lookup a {@link GameObject} when requested using the
 * given identifier.
 *
 * @author Blair Butterworth
 */
public class GameObjectReference implements Supplier<GameObject>
{
    private Identifier reference;
    private transient GameObject cache;
    private transient GameObjectComposite parent;

    public GameObjectReference(GameObject gameObject) {
        this(gameObject.getIdentifier(), gameObject.getRoot(), gameObject);
    }

    public GameObjectReference(Identifier reference, GameObjectComposite parent) {
        this(reference, parent, null);
    }

    private GameObjectReference(Identifier reference, GameObjectComposite parent, GameObject cache) {
        this.reference = reference;
        this.cache = cache;
        this.parent = parent;
    }

    @Override
    public GameObject get() {
        if (cache == null) {
            cache = parent.find(itemWithId(reference));
        }
        return cache;
    }

    public void setParent(GameObjectComposite parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("reference", reference)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        GameObjectReference that = (GameObjectReference)obj;
        return new EqualsBuilder()
            .append(reference, that.reference)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(reference)
            .toHashCode();
    }
}
