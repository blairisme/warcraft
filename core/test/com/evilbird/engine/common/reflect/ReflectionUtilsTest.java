/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.reflect;

import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Instances of this unit test validate the {@link ReflectionUtils} class.
 *
 * @author Blair Butterworth
 */
public class ReflectionUtilsTest
{
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface TestAnnotation {
    }

    private static class TestType {
        boolean invoked;

        private TestType() {
            invoked = false;
        }

        @TestAnnotation
        public void foo() {
            invoked = true;
        }

        public boolean fooInvoked() {
            return invoked;
        }
    }

    @Test
    public void invokeConstructorTest() {
        TestType result = ReflectionUtils.invokeConstructor(TestType.class);
        Assert.assertNotNull(result);
    }

    @Test (expected = NullPointerException.class)
    public void invokeConstructorNullTest() {
        ReflectionUtils.invokeConstructor(null);
    }

    @Test
    public void invokeMethodTest() {
        TestType object = new TestType();
        Assert.assertFalse(object.fooInvoked());

        ReflectionUtils.invokeMethod(object, TestAnnotation.class);
        Assert.assertTrue(object.fooInvoked());
    }

    @Test (expected = NullPointerException.class)
    public void invokeMethodNullObjectTest() {
        ReflectionUtils.invokeMethod(null, TestAnnotation.class);
    }

    @Test (expected = NullPointerException.class)
    public void invokeMethodNullAnnotationTest() {
        TestType object = new TestType();
        ReflectionUtils.invokeMethod(object, null);
    }
}