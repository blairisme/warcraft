/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
