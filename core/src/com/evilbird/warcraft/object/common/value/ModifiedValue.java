/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.common.value;

import com.evilbird.warcraft.object.unit.Unit;

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
