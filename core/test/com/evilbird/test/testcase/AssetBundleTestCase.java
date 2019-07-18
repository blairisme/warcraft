/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.test.testcase;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.common.assets.AssetBundle;
import com.evilbird.test.utils.AssetFileHandleResolver;
import com.evilbird.test.utils.MockFontLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Instances of this test case provide common validation for
 * {@link AssetBundle AssetBundles}.
 *
 * @author Blair Butterworth
 */
public abstract class AssetBundleTestCase<T extends AssetBundle> extends GameTestCase
{
    protected T bundle;
    protected AssetManager assets;
    protected FileHandleResolver resolver;

    @Before
    public void setup() {
        resolver = new AssetFileHandleResolver();
        assets = new AssetManager(resolver);
        bundle = getAssetBundle(assets);
        assets.setLoader(BitmapFont.class, new MockFontLoader(resolver));
    }

    protected abstract T getAssetBundle(AssetManager assets);

    @Test
    public void getTest() throws Exception {
        bundle.load();
        assets.finishLoading();
        for (Method method: bundle.getClass().getMethods()) {
            if (method.getName().startsWith("get") && method.getParameterCount() == 0) {
                Object result = method.invoke(bundle);
                Assert.assertNotNull(result);
            }
        }
    }

    @Test (expected = GdxRuntimeException.class)
    public void getWithoutLoadingTest() throws Throwable {
        try {
            for (Method method : bundle.getClass().getMethods()) {
                if (method.getName().startsWith("get") && method.getParameterCount() == 0) {
                    Object result = method.invoke(bundle);
                    Assert.assertNotNull(result);
                }
            }
        }
        catch (InvocationTargetException error) {
            throw error.getTargetException();
        }
    }

    @Test
    public void assetExistenceTest() {
        for (AssetDescriptor asset: bundle.getAssets()) {
            if (asset.type == I18NBundle.class) {
                assertExists(asset.fileName + ".properties");
            } else {
                assertExists(asset.fileName);
            }
        }
    }

    private void assertExists(String path) {
        FileHandle handle = resolver.resolve(path);
        Assert.assertTrue("Asset doesn't exist: " + path, handle.exists());
    }

    @Test
    public void loadTest() {
        bundle.load();
        assets.finishLoading();

        for (AssetDescriptor asset: bundle.getAssets()) {
            String path = asset.fileName;
            Assert.assertTrue("Asset not loaded: " + path, assets.isLoaded(path));
        }
    }

    @Test
    public void loadAfterLoadTest() {
        bundle.load();
        assets.finishLoading();

        bundle.load();
        assets.finishLoading();

        for (AssetDescriptor asset: bundle.getAssets()) {
            String path = asset.fileName;
            Assert.assertTrue("Asset not loaded: " + path, assets.isLoaded(path));
        }
    }

    @Test
    public void loadAfterUnloadTest() {
        bundle.load();
        assets.finishLoading();

        bundle.unload();
        assets.finishLoading();

        bundle.load();
        assets.finishLoading();

        for (AssetDescriptor asset: bundle.getAssets()) {
            String path = asset.fileName;
            Assert.assertTrue("Asset not loaded: " + path, assets.isLoaded(path));
        }
    }

    @Test
    public void unloadTest() {
        bundle.load();
        assets.finishLoading();

        bundle.unload();
        assets.finishLoading();

        for (AssetDescriptor asset: bundle.getAssets()) {
            String path = asset.fileName;
            Assert.assertFalse("Asset still loaded: " + path, assets.isLoaded(path));
        }
    }

    @Test
    public void unloadAfterUnloadTest() {
        bundle.load();
        assets.finishLoading();

        bundle.unload();
        assets.finishLoading();

        bundle.unload();
        assets.finishLoading();

        for (AssetDescriptor asset: bundle.getAssets()) {
            String path = asset.fileName;
            Assert.assertFalse("Asset still loaded: " + path, assets.isLoaded(path));
        }
    }
}
