/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.text;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Replaces variables in a given string with values. Variables are declared
 * using the syntax: {@code ${variable}}.
 *
 * @author Blair Butterworth
 */
public class StringSubstitutor
{
    private static final String TOKEN_PREFIX = "${";
    private static final String TOKEN_SUFFIX = "}";

    private Map<String, String> variables;

    public StringSubstitutor(Map<String, String> variables) {
        this.variables = new HashMap<>(variables.size());
        for (Entry<String, String> entry: variables.entrySet()) {
            this.variables.put(TOKEN_PREFIX + entry.getKey() + TOKEN_SUFFIX, entry.getValue());
        }
    }

    public String replace(String text) {
        String result = text;
        for (Entry<String, String> entry: variables.entrySet()) {
            result = result.replace(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
