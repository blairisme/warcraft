/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.state;

import com.evilbird.engine.item.ItemRoot;

public interface StateFactory
{
    void load();

    ItemRoot get(StateIdentifier identifier);
}
