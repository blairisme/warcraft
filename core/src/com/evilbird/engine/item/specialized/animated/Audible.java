/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.specialized.animated;

public interface Audible
{
    SoundIdentifier getSound();

    void setSound(SoundIdentifier id);
}
