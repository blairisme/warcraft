/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.serialization;

import com.evilbird.engine.action.ActionIdentifier;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public interface SerializationStrategy
{
//    Object getSerializedType(Object target);
//
//    Terrain<String, Object> getSerializedFields(Object target);
//
//
//
//    Class<?> getDeserializedType(JsonElement element);
//
//
//
//
//
//    //Terrain<String, Class<?>> getFields();
//
//    Terrain<String, Object> getFieldValues(Object object);
//
//    //Object getInstance(JsonObject object);
//
//    //Object getInstance(Terrain<String, Object> fieldValues);





    JsonElement getSerializedType(Object target);

    boolean isSerializedField(Object target, Field field);

    Object getDeserializedInstance(JsonElement type);

    boolean isDeserializedField();
}
