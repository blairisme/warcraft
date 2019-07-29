/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.test.data.assets;

import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.test.utils.MockBundle;
import com.evilbird.test.utils.TestFileHandleResolver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestStringBundles
{
    private TestStringBundles() {
    }

    public static I18NBundle newTestBundle() {
        return MockBundle.createBundle(new TestFileHandleResolver(), "/warcraft/asset/bundle.properties");
    }

    public static I18NBundle newMockBundle() {
        I18NBundle bundle = mock(I18NBundle.class);
        //when(bundle.get(anyString())).thenReturn("value");
        when(bundle.format(anyString(), any())).thenReturn("value");
        when(bundle.format(anyString(), any(), any())).thenReturn("value");
        return bundle;
    }
}
