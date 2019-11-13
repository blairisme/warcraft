/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.common.value;

import com.evilbird.warcraft.object.unit.Unit;
import com.google.gson.annotations.JsonAdapter;

/**
 * A value placeholder allowing the value to be computed on request.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(ValueSerializer.class)
public interface Value
{
    float getValue(Unit unit);
}
