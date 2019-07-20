/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.test.utils;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A {@link FileHandleResolver} implementation that provides access to Warcraft
 * asset files.
 *
 * @author Blair Butterworth
 */
public class AssetFileHandleResolver implements FileHandleResolver
{
    private Path assets;

    public AssetFileHandleResolver() {
        Path directory = getWorkingDirectory();

        while (directory != null && !Files.exists(directory.resolve("LICENSE"))) {
            directory = directory.getParent();
        }
        if (directory == null) {
            throw new UnsupportedOperationException("Unable to locate root directory");
        }
        assets = directory.resolve("android/assets");
    }

    private Path getWorkingDirectory() {
        String result = System.getenv("TRAVIS_BUILD_DIR");
        if (result == null) {
            result = System.getProperty("user.dir");
        }
        return Paths.get(result);
    }

    @Override
    public FileHandle resolve(String fileName) {
        Path path = assets.resolve(fileName);
        File file = path.toFile();
        return new FileHandle(file);
    }
}
