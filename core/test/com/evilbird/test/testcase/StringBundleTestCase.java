/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
