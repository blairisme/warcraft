/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.control;

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
