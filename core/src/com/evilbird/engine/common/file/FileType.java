/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
    TTF,
    TMX;

    public String getFileExtension() {
        return "." +  getExtension();
    }

    public String getExtension() {
        switch (this) {
            case MP3: return "mp3";
            case JSON: return "json";
            case PNG: return "png";
            case TTF: return "ttf";
            case TMX: return "tmx";
            default: throw new UnsupportedOperationException();
        }
    }
}
