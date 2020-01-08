/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.file;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

import java.io.File;

/**
 * A {@link FileHandleResolver} implementation where all paths are provided
 * relative to a given directory.
 *
 * @author Blair Butterworth
 */
public class RelativeFileHandleResolver implements FileHandleResolver
{
    private String directory;
    private FileHandleResolver resolver;

    public RelativeFileHandleResolver(String directory, FileHandleResolver resolver) {
        this.directory = directory;
        this.resolver = resolver;
    }

    @Override
    public FileHandle resolve(String name) {
        return resolver.resolve(directory + File.separator + name);
    }
}
