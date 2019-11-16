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
import com.evilbird.engine.common.assets.AssetBundle;
import com.evilbird.engine.common.graphics.animation.Animation;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.test.utils.AssetFileHandleResolver;
import com.evilbird.test.utils.TestAssetManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Instances of this test case validate AnimationCatalog classes.
 *
 * @author Blair Butterworth
 */
public abstract class AnimationCatalogTestCase<C extends AnimationCatalog, A extends AssetBundle> extends GameTestCase
{
    protected A assets;
    protected C catalog;
    protected AssetManager manager;
    protected FileHandleResolver resolver;

    @Before
    public void setup() {
        resolver = new AssetFileHandleResolver();
        manager = TestAssetManager.getTestAssetManager(resolver);
        assets = newAssets(manager);
        assets.load();
        manager.finishLoading();
        catalog = newCatalog(assets);
    }

    protected abstract A newAssets(AssetManager manager);

    protected abstract C newCatalog(A assets);

    @Test
    public void getTest() {
        Map<Identifier, Animation> actual = catalog.get();
        Collection<Identifier> expected = getAnimationsIds();

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.size(), actual.size());

        for (Entry<Identifier, Animation> entry: actual.entrySet()) {
            Identifier id = entry.getKey();
            Animation animation = entry.getValue();

            Assert.assertTrue(id + " missing", expected.contains(id));
            Assert.assertNotNull(animation);
        }
    }

    protected abstract Collection<Identifier> getAnimationsIds();
}
