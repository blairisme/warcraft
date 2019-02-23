/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.lang;

import com.badlogic.gdx.scenes.scene2d.Touchable;

public interface Disablable
{
    boolean getTouchable();

    void setTouchable(Touchable touchable);
}
