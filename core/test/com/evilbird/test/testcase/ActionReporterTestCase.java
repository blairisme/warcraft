/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.test.testcase;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.events.EventQueue;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Instances of this test case provide common validation for action reporters.
 *
 * @author Blair Butterworth
 */
public abstract class ActionReporterTestCase extends GameTestCase
{
    private Object reporter;
    private EventQueue events;

    @Before
    public void setup() {
        try {
            events = new EventQueue();
            reporter = ConstructorUtils.invokeConstructor(getReporterType(), events);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract Class<?> getReporterType();

    protected abstract Collection<Class<?>> getReportedTypes();

    protected abstract Class<? extends Event> getReportedEvent();

    @Test
    public void eventTest() throws Exception {
        Class<? extends Event> reportedEvent = getReportedEvent();
        for (Class<?> reportedType: getReportedTypes()) {
            for (Method method: reportedType.getMethods()) {
                events.clear();
                Assert.assertFalse(events.hasEvents(reportedEvent));
                method.invoke(reporter, getMockParameters(method));
                Assert.assertTrue(events.hasEvents(reportedEvent));
            }
        }
    }

    private Object[] getMockParameters(Method method) {
        List<Object> result = new ArrayList<>();
        for (Class<?> parameter: method.getParameterTypes()){
            result.add(Mockito.mock(parameter));
        }
        return result.toArray();
    }
}
