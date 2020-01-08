/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.testcase;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.test.utils.AssetFileHandleResolver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Provides common validation for string bundle wrapper classes.
 *
 * @param <T> the type of the string bundle wrapper.
 *
 * @author Blair Butterworth
 */
public abstract class StringBundleTestCase<T>
{
    protected T strings;
    protected AssetManager assets;
    protected FileHandleResolver resolver;

    @Before
    public void setup() {
        resolver = new AssetFileHandleResolver();
        assets = new AssetManager(resolver);
        String path = getBundleAsset();
        assets.load(path, I18NBundle.class);
        assets.finishLoadingAsset(path);
        strings = getBundleWrapper(assets.get(path, I18NBundle.class));
    }

    protected abstract T getBundleWrapper(I18NBundle bundle);

    protected abstract String getBundleAsset();

    @Test
    public void getTest() throws Exception {
        for (Method method: strings.getClass().getMethods()) {
            if (method.getName().startsWith("get")
                && method.getParameterCount() == 0
                && method.getReturnType() == String.class)
            {
                String result = (String)method.invoke(strings);
                Assert.assertNotNull(result);
                Assert.assertFalse(result.isEmpty());
            }
        }
    }
}
