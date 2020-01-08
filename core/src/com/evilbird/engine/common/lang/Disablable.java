/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.lang;

import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * Implementors of this interface represent an object that the user can
 * interact with.
 *
 * @author Blair Butterworth
 */
public interface Disablable
{
    boolean getTouchable();

    void setTouchable(Touchable touchable);
}
