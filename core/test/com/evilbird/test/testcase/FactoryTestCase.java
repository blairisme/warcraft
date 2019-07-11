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
import com.evilbird.engine.game.GameFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.evilbird.test.data.device.TestAssets.newAssetManagerMock;

/**
 * Instances of this test case provide common validation for factories.
 *
 * @author Blair Butterworth
 */
public abstract class FactoryTestCase<T> extends GameTestCase
{
    protected GameFactory<T> factory;
    protected AssetManager assets;

    @Before
    public void setup() {
        assets = newAssetManagerMock();
        factory = newFactory(assets);
    }

    protected abstract GameFactory<T> newFactory(AssetManager assets);

    @Test
    @SuppressWarnings("unchecked")
    public void loadTest() throws Exception {
        factory.load(null);

        ArgumentCaptor<String> pathArguments = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Class> typeArguments = ArgumentCaptor.forClass(Class.class);
        Mockito.verify(assets, Mockito.atLeastOnce()).load(pathArguments.capture(), typeArguments.capture());
        List<String> paths = pathArguments.getAllValues();

        for (Field field: FieldUtils.getAllFields(factory.getClass())) {
            if (Modifier.isStatic(field.getModifiers()) && field.getType() == String.class) {
                field.setAccessible(true);
                String assetPath = (String)field.get(factory);
                boolean loaded = paths.stream().anyMatch(path -> path.startsWith(assetPath));
                Assert.assertTrue("Asset not loaded: " + assetPath, loaded);
            }
        }
    }

    @Test
    public void getTest() throws Exception {
        T newObject = factory.get(null);
        Assert.assertNotNull(newObject);

        for (Entry<String, Object> property: newValueProperties().entrySet()) {
            String name = property.getKey();
            Object expected = property.getValue();
            Object actual = MethodUtils.invokeMethod(newObject, "get" + StringUtils.capitalize(name));
            Assert.assertEquals(expected, actual);
        }
    }

    protected abstract Map<String, Object> newValueProperties();
}
