/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.text;

/**
 * Utility class for converting between various ASCII case formats.
 *
 * @author Blair Butterworth
 */
public class CaseUtils
{
    private CaseUtils() {
    }

    public static String toSnakeCase(String text) {
        StringBuilder result = new StringBuilder();
        char[] characters = text.toCharArray();
        for (int i = 0; i < characters.length; ++i) {
            char character = characters[i];
            if (Character.isSpaceChar(character)) {
                result.append("_");
            }
            else if (Character.isUpperCase(character) && i != 0) {
                result.append("_");
                result.append(Character.toLowerCase(character));
            }
            else {
                result.append(Character.toLowerCase(character));
            }
        }
        return result.toString();
    }
}
