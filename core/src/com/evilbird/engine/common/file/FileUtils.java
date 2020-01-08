/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.file;

import java.io.File;

/**
 * General file manipulation utilities, based on Apache Commons IO FileUtils.
 *
 * <p>
 * The difference between this and Apache Commons IO FileUtils are:
 * </p>
 *
 * <ul>
 * <li>
 *     Stripped out all functions except deleteQuietly.
 * </li>
 * <li>
 *    Replaced clean directory call in deleteQuietly with recursive deletion.
 * </li>
 * </ul>
 *
 * @author Unknown
 * @author Blair Butterworth
 */
public class FileUtils
{
    /**
     * Disable construction of static utility class.
     */
    private FileUtils() {
    }

    /**
     * Deletes a file, never throwing an exception. If file is a directory,
     * delete it and all sub-directories.
     *
     * <p>
     * The difference between File.delete() and this method are:
     * </p>
     *
     * <ul>
     * <li>
     *     A directory to be deleted does not have to be empty.
     * </li>
     * <li>
     *     No exceptions are thrown when a file or directory cannot be deleted.
     * </li>
     * </ul>
     *
     * @param file  file or directory to delete, can be {@code null}
     * @return      {@code true} if the file or directory was deleted,
     *              otherwise {@code false}.
     */
    public static boolean deleteQuietly(final File file) {
        if (file == null) {
            return false;
        }
        try {
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                if (children != null) {
                    for (File child: children) {
                        deleteQuietly(child);
                    }
                }
            }
        } catch (final Exception ignored) {
            // ignore
        }
        try {
            return file.delete();
        } catch (final Exception ignored) {
            return false;
        }
    }
}
