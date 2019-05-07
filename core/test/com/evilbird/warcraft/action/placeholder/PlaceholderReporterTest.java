/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.engine.events.Event;
import com.evilbird.test.testcase.ActionReporterTestCase;

import java.util.Collection;
import java.util.Collections;

/**
 * Instances of this unit test validate the {@link PlaceholderReporter} class.
 *
 * @author Blair Butterworth
 */
public class PlaceholderReporterTest extends ActionReporterTestCase
{
    @Override
    protected Class<?> getReporterType() {
        return PlaceholderReporter.class;
    }

    @Override
    protected Collection<Class<?>> getReportedTypes() {
        return Collections.singleton(PlaceholderObserver.class);
    }

    @Override
    protected Class<? extends Event> getReportedEvent() {
        return PlaceholderEvent.class;
    }
}