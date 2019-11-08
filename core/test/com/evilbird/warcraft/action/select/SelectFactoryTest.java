/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.test.testcase.ActionFactoryTestCase;
import com.evilbird.test.utils.MockInjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.selection.SelectActions;
import com.evilbird.warcraft.action.selection.SelectArea;
import com.evilbird.warcraft.action.selection.SelectFactory;
import com.evilbird.warcraft.action.selection.SelectInvert;

/**
 * Instances of this unit test validate the {@link com.evilbird.warcraft.action.selection.SelectFactory} class.
 *
 * @author Blair Butterworth
 */
public class SelectFactoryTest extends ActionFactoryTestCase
{
    @Override
    protected ActionProvider newFactory() {
        InjectedPool<com.evilbird.warcraft.action.selection.SelectArea> areaPool = new MockInjectedPool<>(SelectArea.class);
        InjectedPool<com.evilbird.warcraft.action.selection.SelectInvert> selectInvertPool = new MockInjectedPool<>(SelectInvert.class);
        return new SelectFactory(areaPool, selectInvertPool);
    }

    @Override
    protected ActionIdentifier[] getIdentifiers() {
        return SelectActions.values();
    }
}