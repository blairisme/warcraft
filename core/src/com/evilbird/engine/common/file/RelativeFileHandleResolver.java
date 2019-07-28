/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
