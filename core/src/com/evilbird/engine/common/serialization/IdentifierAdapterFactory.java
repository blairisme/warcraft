/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.serialization;

import com.evilbird.engine.common.lang.Identifier;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

public class IdentifierAdapterFactory implements TypeAdapterFactory
{
    private IdentifierAdapter adapter;

    public IdentifierAdapterFactory() {
        adapter = new IdentifierAdapter();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<?> type = typeToken.getRawType();
        if (Identifier.class.isAssignableFrom(type)) {
            return (TypeAdapter<T>)adapter;
        }
        return null;
    }
}
