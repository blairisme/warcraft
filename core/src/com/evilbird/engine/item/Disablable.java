/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.badlogic.gdx.scenes.scene2d.Touchable;

//TODO: Move into common
public interface Disablable
{
    public boolean getTouchable();

    public void setTouchable(Touchable touchable);
}
