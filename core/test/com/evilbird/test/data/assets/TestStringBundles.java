/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
