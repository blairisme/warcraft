/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio;

/**
 * Values in this enumeration define common sound file types.
 *
 * @author Blair Butterworth
 */
public enum SoundType
{
    MP3;

    public String getFileExtension() {
        switch (this) {
            case MP3: return ".mp3";
            default: throw new UnsupportedOperationException();
        }
    }
}
