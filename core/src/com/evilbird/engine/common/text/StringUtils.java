/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.text;

/**
 * Utility class containing common string manipulation methods, including
 * methods for converting between various ASCII case formats.
 *
 * @author Blair Butterworth
 */
public class StringUtils
{
    private StringUtils() {
    }

    /**
     * Generates a string of the given length containing the given character.
     */
    public static String newString(int length, char character) {
        StringBuilder builder = new StringBuilder(length);
        for(int i=0; i < length; i++){
            builder.append(character);
        }
        return builder.toString();
    }

    /**
     * Updates the character at the given index.
     */
    public static String setCharAt(String text, int index, char character) {
        StringBuilder builder = new StringBuilder(text);
        builder.setCharAt(index, character);
        return builder.toString();
    }

    /**
     * Converts the given text into snake case, a textual representation using
     * lower case characters and where words are separated using underscores.
     */
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
