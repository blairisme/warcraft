/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
