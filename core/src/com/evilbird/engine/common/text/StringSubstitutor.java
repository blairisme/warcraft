/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.text;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

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

    /**
     * Creates a new instance of this class given a {@link Map} of key value
     * pairs. Keys are specified with out the substitution prefix "${".
     */
    public StringSubstitutor(Map<String, String> variables) {
        Objects.requireNonNull(variables);
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
