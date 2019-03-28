/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.evilbird.engine.common.function.Supplier;
import com.evilbird.engine.common.lang.Identifier;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.evilbird.engine.item.utility.ItemPredicates.itemWithId;

/**
 * Instances of this class lookup an {@link Item} when requested using the
 * given identifier.
 *
 * @author Blair Butterworth
 */
public class ItemReference implements Supplier<Item>
{
    private Identifier reference;
    private transient Item cache;
    private transient ItemComposite parent;

    public ItemReference(Item item) {
        this(item.getIdentifier(), item.getRoot(), item);
    }

    public ItemReference(Identifier reference, ItemComposite parent) {
        this(reference, parent, null);
    }

    private ItemReference(Identifier reference, ItemComposite parent, Item cache) {
        this.reference = reference;
        this.cache = cache;
        this.parent = parent;
    }

    @Override
    public Item get() {
        if (cache == null) {
            cache = parent.find(itemWithId(reference));
        }
        return cache;
    }

    public void setParent(ItemComposite parent) {
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
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        ItemReference that = (ItemReference)obj;
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
