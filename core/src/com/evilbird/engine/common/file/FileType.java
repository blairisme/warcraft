/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.file;

/**
 * Values in this enumeration define common sound file types.
 *
 * @author Blair Butterworth
 */
public enum FileType
{
    JSON,
    MP3,
    PNG,
    TTF;

    public String getFileExtension() {
        return "." +  getExtension();
    }

    public String getExtension() {
        switch (this) {
            case MP3: return "mp3";
            case JSON: return "json";
            case PNG: return "png";
            case TTF: return "ttf";
            default: throw new UnsupportedOperationException();
        }
    }
}
