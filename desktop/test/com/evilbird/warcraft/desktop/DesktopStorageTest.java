/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.desktop;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DesktopStorageTest
{
    private DesktopStorage storage;
    private FileHandleResolver resolver;

    @Before
    public void setup() {
        resolver = Mockito.mock(FileHandleResolver.class);
        storage = new DesktopStorage(resolver);
    }

    @Test
    public void listTest() throws IOException {
        FileHandle file1 = mockFileHandle("a/b/c/1.json");
        FileHandle file2 = mockFileHandle("a/b/c/2.json");
        FileHandle file3 = mockFileHandle("a/b/c/3.json");
        FileHandle directory = mockFileHandle("a/b/c");
        FileHandle[] files = {file1, file2, file3};

        Mockito.when(directory.list()).thenReturn(files);
        Mockito.when(resolver.resolve(Mockito.anyString())).thenReturn(directory);

        List<String> expected = Arrays.asList("a/b/c/1.json", "a/b/c/2.json", "a/b/c/3.json");
        List<String> actual = storage.list("a/b/c");

        Assert.assertEquals(expected, actual);
    }

    @Test (expected = IOException.class)
    public void listErrorTest() throws IOException {
        Mockito.when(resolver.resolve(Mockito.anyString())).thenThrow(GdxRuntimeException.class);
        storage.list("a/b/c");
    }

    private FileHandle mockFileHandle(String path) {
        FileHandle handle = Mockito.mock(FileHandle.class);
        Mockito.when(handle.path()).thenReturn(path);
        return handle;
    }
}