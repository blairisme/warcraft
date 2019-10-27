/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.value;

import com.evilbird.warcraft.item.unit.Unit;

/**
 * Implementors of this interface represent a {@link Value} that has been
 * augmented in some way.
 *
 * @author Blair Butterworth
 */
public interface ModifiedValue extends Value
{
    float getBaseValue(Unit unit);
}
