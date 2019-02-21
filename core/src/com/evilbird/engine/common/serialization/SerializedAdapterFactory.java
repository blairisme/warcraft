/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.serialization;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.reflect.ConstructorUtils;

public class SerializedAdapterFactory implements TypeAdapterFactory
{
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class type = typeToken.getRawType();
        if (type.isAnnotationPresent(Serialized.class) ){
            Serialized serialized = (Serialized)type.getAnnotation(Serialized.class);
            Class<? extends TypeAdapter> adapterType = serialized.adapter();
            return invokeConstructor(adapterType);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private <T> T invokeConstructor(Class<? extends TypeAdapter> adapterType) {
        try {
            return (T)ConstructorUtils.invokeConstructor(adapterType);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
