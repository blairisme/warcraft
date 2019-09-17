/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.specialized;

/**
 * Implementors of this interface provide a method that is called when items in
 * a {@link ListPane} are selected.
 *
 * @param <T> the type of items in the {@code ListPane}.
 *
 * @author Blair Butterworth
 */
public interface ListSelectionListener<T>
{
    void onSelected(T selected);
}
