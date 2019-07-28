/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

/**
 * Provides common utility functions for working with the GSON serialization
 * library.
 *
 * @author Blair Butterworth
 */
public class SerializerUtils
{
    private SerializerUtils() {
    }

    public static void merge(JsonObject source, JsonObject recipient) {
        for (Map.Entry<String, JsonElement> entry: source.entrySet()) {
            recipient.add(entry.getKey(), entry.getValue());
        }
    }
}
