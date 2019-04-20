/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.utils;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

import java.io.File;
import java.net.URI;
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
            URI uri = url.toURI();
            File file = new File(uri);
            return new FileHandle(file);
        }
        catch (URISyntaxException error) {
            throw new RuntimeException(error);
        }
    }
}
