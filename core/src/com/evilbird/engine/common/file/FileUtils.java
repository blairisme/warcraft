/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
