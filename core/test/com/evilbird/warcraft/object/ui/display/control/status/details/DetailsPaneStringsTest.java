/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.ui.display.control.status.details;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.test.utils.AssetFileHandleResolver;
import com.evilbird.warcraft.object.display.control.status.details.DetailsPaneStrings;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static com.evilbird.test.data.item.TestSkins.newTestSkin;

/**
 * Instances of this unit test validate the {@link com.evilbird.warcraft.object.display.control.status.details.DetailsPaneStrings} class.
 *
 * @author Blair Butterworth
 */
public class DetailsPaneStringsTest
{
    private static final String DETAILS_BUNDLE = "data/strings/common/menu/details";
    private static final String NAMES_BUNDLE = "data/strings/common/menu/names";

    private com.evilbird.warcraft.object.display.control.status.details.DetailsPaneStrings strings;

    @Before
    public void setup() {
        FileHandleResolver resolver = new AssetFileHandleResolver();
        AssetManager assets = new AssetManager(resolver);
        assets.load(DETAILS_BUNDLE, I18NBundle.class);
        assets.load(NAMES_BUNDLE, I18NBundle.class);
        assets.finishLoading();

        I18NBundle detailsBundle = assets.get(DETAILS_BUNDLE, I18NBundle.class);
        I18NBundle namesBundle = assets.get(NAMES_BUNDLE, I18NBundle.class);
        strings = new DetailsPaneStrings(detailsBundle, namesBundle);
    }

    @Test
    public void getTest() throws Exception {
        for (Method method: strings.getClass().getMethods()) {
            if (method.getName().startsWith("get") && method.getReturnType() == String.class) {
                String result = invoke(method);
                Assert.assertNotNull(result);
                Assert.assertFalse(result.isEmpty());
            }
        }
    }

    private String invoke(Method method) throws Exception {
        if (method.getParameterCount() == 0) {
            return (String)method.invoke(strings);
        }
        if (method.getParameterCount() == 1 && method.getParameterTypes()[0] == float.class) {
            return (String)method.invoke(strings, 1.23f);
        }
        if (method.getParameterCount() == 1 && method.getParameterTypes()[0] == int.class) {
            return (String)method.invoke(strings, 1);
        }
        if (method.getParameterCount() == 1 && method.getParameterTypes()[0] == Unit.class) {
            return (String)method.invoke(strings, newTestUnit());
        }
        if (method.getParameterCount() == 1 && method.getParameterTypes()[0] == UnitType.class) {
            return (String)method.invoke(strings, UnitType.AltarOfStorms);
        }
        if (method.getParameterCount() == 2
            && method.getParameterTypes()[0] == int.class
            && method.getParameterTypes()[1] == int.class) {
            return (String)method.invoke(strings, 1, 1);
        }
        if (method.getParameterCount() == 3
            && method.getParameterTypes()[0] == int.class
            && method.getParameterTypes()[1] == int.class
            && method.getParameterTypes()[1] == int.class) {
            return (String)method.invoke(strings, 1, 1, 1);
        }
        throw new UnsupportedOperationException(method.getName());
    }

    private Unit newTestUnit() {
        Unit unit = new Unit(newTestSkin());
        unit.setType(UnitType.ElvenArcher);
        return unit;
    }
}