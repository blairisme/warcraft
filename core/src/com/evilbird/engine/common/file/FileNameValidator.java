/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.file;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates file names against operating system file name restrictions.
 *
 * @author Blair Butterworth
 */
public class FileNameValidator
{
    private static Pattern pattern;

    public FileNameValidator() {
        if (pattern == null) {
            pattern = Pattern.compile(
                "# Match a valid Windows filename (unspecified file system).          \n" +
                "^                                # Anchor to start of string.        \n" +
                "(?!                              # Assert filename is not: CON, PRN, \n" +
                "  (?:                            # AUX, NUL, COM1, COM2, COM3, COM4, \n" +
                "    CON|PRN|AUX|NUL|             # COM5, COM6, COM7, COM8, COM9,     \n" +
                "    COM[1-9]|LPT[1-9]            # LPT1, LPT2, LPT3, LPT4, LPT5,     \n" +
                "  )                              # LPT6, LPT7, LPT8, and LPT9...     \n" +
                "  (?:\\.[^.]*)?                  # followed by optional extension    \n" +
                "  $                              # and end of string                 \n" +
                ")                                # End negative lookahead assertion. \n" +
                "[^<>:\"/\\\\|?*\\x00-\\x1F]*     # Zero or more valid filename chars.\n" +
                "[^<>:\"/\\\\|?*\\x00-\\x1F\\ .]  # Last char is not a space or dot.  \n" +
                "$                                # Anchor to end of string.            ",
                Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.COMMENTS);
        }
    }

    public boolean isValidName(String text) {
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
