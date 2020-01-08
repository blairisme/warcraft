/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.verifier;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.EqualsVerifierApi;
import nl.jqno.equalsverifier.Warning;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.mockito.Mockito;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("unchecked")
public class EqualityVerifier
{
    public static EqualityVerifier forClass(Class<?> type) {
        return new EqualityVerifier(type);
    }

    private Class<?> type;
    private EqualsVerifierApi verifier;

    private EqualityVerifier(Class<?> type) {
        this.type = type;
        this.verifier = EqualsVerifier.forClass(type);
        this.verifier.suppress(Warning.NONFINAL_FIELDS);
        this.verifier.usingGetClass();
        //excludeSyntheticFields();
    }

    public EqualityVerifier suppress(Warning ... warnings) {
        verifier.suppress(warnings);
        return this;
    }

    public EqualityVerifier includeFields(String ... fields) {
        verifier.withOnlyTheseFields(fields);
        return this;
    }

    public EqualityVerifier excludeFields(String ... fields) {
        verifier.withIgnoredFields(fields);
        return this;
    }

    public EqualityVerifier excludeTransientFields() {
        Collection<String> transientFields = new ArrayList<>();
        for (Field field: FieldUtils.getAllFields(type)) {
            if (!field.isSynthetic() && Modifier.isTransient(field.getModifiers())) {
                transientFields.add(field.getName());
            }
        }
        return excludeFields(transientFields.toArray(new String[0]));
    }

    public EqualityVerifier withMockedTransientFields() {
        return withMockedTransientFields(type);
    }

    public EqualityVerifier withMockedTransientFields(Class<?> type) {
        for (Field field: FieldUtils.getAllFields(type)) {
            if (Modifier.isTransient(field.getModifiers())) {
                Class<?> fieldType = field.getType();
                if (! fieldType.isPrimitive()) {
                    withMockedType(fieldType);
                }
            }
        }
        return this;
    }

    public EqualityVerifier withMockedType(Class<?> type) {
        Object red = newMockedValue(type);
        Object black = newMockedValue(type);
        verifier.withPrefabValues(type, red, black);
        return this;
    }

    private Object newMockedValue(Class<?> type) {
        if (type.isArray()){
            return newMockedArray(type);
        }
        return Mockito.mock(type);
    }

    private Object newMockedArray(Class<?> type) {
        int dimensions = getDimensions(type);
        Class<?> elementType = getElementType(type);
        return Array.newInstance(elementType, 1, dimensions);
    }

    private Class<?> getElementType(Class<?> type) {
        Class<?> current = type;
        while (current.isArray()) {
            current = current.getComponentType();
        }
        return current;
    }

    private int getDimensions(Class<?> type) {
        int dimensions = 0;
        Class<?> current = type;
        while (current.isArray()) {
            dimensions++;
            current = current.getComponentType();
        }
        return dimensions;
    }

    public void verify() {
        verifier.verify();
    }
}
