/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.events.Event;
import com.evilbird.test.testcase.ActionReporterTestCase;

import java.util.Map;

/**
 * Instances of this unit test validate the {@link SelectReporter} class.
 *
 * @author Blair Butterworth
 */
public class SelectReporterTest extends ActionReporterTestCase
{
    @Override
    protected Class<?> getReporterType() {
        return SelectReporter.class;
    }

    @Override
    protected Map<Class<?>, Class<? extends Event>> getReportedTypes() {
        return Maps.of(SelectObserver.class, SelectEvent.class);
    }
}