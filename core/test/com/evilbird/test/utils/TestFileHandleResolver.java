/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.utils;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TestFileHandleResolver implements FileHandleResolver
{
    private Map<String, String> associations;
    private Map<String, FileHandle> responses;

    public TestFileHandleResolver() {
        associations = new HashMap<>();
        responses = new HashMap<>();
    }

    public void respondWith(String alias, String actual) {
        associations.put(alias, actual);
    }

    public void respondWith(String name, FileHandle handle) {
        responses.put(name, handle);
    }

    public String fullPath(String relativePath) {
        FileHandle handle = resolve(relativePath);
        return handle.path();
    }

    @Override
    public FileHandle resolve(String name) {
        if (responses.containsKey(name)) {
            return responses.get(name);
        }
        if (associations.containsKey(name)) {
            return getHandle(associations.get(name));
        }
        return getHandle(name);
    }

    public static FileHandle getHandle(String name) {
        try {
            URL url = TestFileHandleResolver.class.getResource(name);
            File file = url != null ? new File(url.toURI()) : new File(name);
            return new FileHandle(file);
        }
        catch (URISyntaxException error) {
            throw new RuntimeException(error);
        }
    }
}
