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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.test.utils.AssetFileHandleResolver;
import com.evilbird.test.utils.TestAssetManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.Map;

/**
 * Instances of this test case provide validation for {@link GameFactory}
 * implementations.
 *
 * @author Blair Butterworth
 */
public abstract class GameFactoryTestCase<T extends GameFactory> extends GameTestCase
{
    protected GameFactory factory;
    protected AssetManager assets;
    protected FileHandleResolver resolver;
    protected DeviceDisplay display;

    @Before
    public void setup() {
        resolver = new AssetFileHandleResolver();
        assets = TestAssetManager.getTestAssetManager(resolver);
        display = Mockito.mock(DeviceDisplay.class);
        factory = newFactory(display, assets);
    }

    protected abstract T newFactory(DeviceDisplay display, AssetManager assets);

    @Test
    public void loadUnloadTest() {
        Assert.assertEquals(0, assets.getAssetNames().size);
        for (Identifier context: getLoadContexts()) {
            factory.load(context);
            assets.finishLoading();
            Assert.assertTrue(assets.getAssetNames().size > 0);

            factory.unload(context);
            assets.finishLoading();
            Assert.assertEquals(0, assets.getAssetNames().size);
        }
    }

//    @Test
//    public void unloadBeforeLoadTest() {
//        Collection<Identifier> contexts = getLoadContexts();
//        Identifier context = contexts.iterator().next();
//
//        factory.unload(context);
//        factory.load(context);
//    }

    protected abstract Collection<Identifier> getLoadContexts();

    @Test
    public void getTest() throws Exception {
        Collection<Identifier> contexts = getLoadContexts();
        Identifier context = contexts.iterator().next();

        factory.load(context);
        assets.finishLoading();

        Collection<Identifier> types = getValueTypes();
        Identifier type = types.iterator().next();

        Object newObject = factory.get(type);
        Assert.assertNotNull(newObject);

        for (Map.Entry<String, Object> property: getValueProperties().entrySet()) {
            String name = property.getKey();
            Object expected = property.getValue();
            Object actual = MethodUtils.invokeMethod(newObject, "get" + StringUtils.capitalize(name));
            Assert.assertEquals(expected, actual);
        }
    }

    protected abstract Collection<Identifier> getValueTypes();

    protected abstract Map<String, Object> getValueProperties();
}
