/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
