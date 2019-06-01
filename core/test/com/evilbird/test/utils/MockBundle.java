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
import com.badlogic.gdx.utils.I18NBundle;

import java.io.IOException;
import java.io.Reader;

public class MockBundle extends I18NBundle
{
    private MockBundle() {
    }

    public static MockBundle createBundle(FileHandleResolver resolver, String path) {
        FileHandle handle = resolver.resolve(path);
        try (Reader reader = handle.reader()) {
            MockBundle bundle = new MockBundle();
            bundle.load(reader);
            return bundle;
        }
        catch (IOException error) {
            throw new RuntimeException(error);
        }
    }
}
