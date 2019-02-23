/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.verifier;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.EqualsVerifierApi;
import nl.jqno.equalsverifier.Warning;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;

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
            if (Modifier.isTransient(field.getModifiers())) {
                transientFields.add(field.getName());
            }
        }
        return excludeFields(transientFields.toArray(new String[0]));
    }

    public EqualityVerifier withMockedType(Class<?> type) {
        Object red = Mockito.mock(type);
        Object black = Mockito.mock(type);
        verifier.withPrefabValues(type, red, black);
        return this;
    }

    public EqualityVerifier withMockedTransientFields() {
        for (Field field: FieldUtils.getAllFields(type)) {
            if (Modifier.isTransient(field.getModifiers())) {
                if (! field.getType().isPrimitive()) {
                    withMockedType(field.getType());
                }
            }
        }
        return this;
    }

    public void verify() {
        verifier.verify();
    }
}
