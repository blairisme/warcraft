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
