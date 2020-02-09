/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.data.product;

/**
 * Implementors of this interface represent something that can be produced by a
 * {@code GameObject}. For example a soldier or an attack upgrade.
 *
 * @author Blair Butterworth
 */
public interface Product
{
    /**
     * Returns a {@link Production} instance defining the resources and times
     * required to produce the {@code Product}.
     */
    Production getProduction();
}
