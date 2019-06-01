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
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

    protected abstract Map<Class<?>, Class<? extends Event>> getReportedTypes();

    @Test
    public void eventTest() throws Exception {
        for (Entry<Class<?>, Class<? extends Event>> reported: getReportedTypes().entrySet()) {
            Class<?> reportedType = reported.getKey();
            Class<? extends Event> reportedEvent = reported.getValue();

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
            result.add(getMockParameter(parameter));
        }
        return result.toArray();
    }

    @SuppressWarnings("unchecked")
    private Object getMockParameter(Class<?> parameter) {
        if (parameter == boolean.class || parameter == Boolean.class) {
            return true;
        }
        if (parameter == float.class|| parameter == Float.class) {
            return 0f;
        }
        if (parameter.isEnum()) {
            EnumSet enumset = EnumSet.allOf((Class<? extends Enum>)parameter);
            return enumset.iterator().next();
        }
        return Mockito.mock(parameter);
    }
}
