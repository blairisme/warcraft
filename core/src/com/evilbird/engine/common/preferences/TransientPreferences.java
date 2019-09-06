/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.preferences;

import com.badlogic.gdx.Preferences;

import java.util.HashMap;
import java.util.Map;

/**
 * A {@link Preferences Preference} implementation that only stores property
 * values in memory.
 *
 * @author Blair Butterworth
 */
public class TransientPreferences implements Preferences 
{
    private Map<String, Object> properties;

    public TransientPreferences() {
        properties = new HashMap<>();
    }
    
    @Override
    public Preferences putBoolean(String key, boolean value) {
        properties.put(key, Boolean.toString(value));
        return this;
    }

    @Override
    public Preferences putInteger(String key, int value) {
        properties.put(key, Integer.toString(value));
        return this;
    }

    @Override
    public Preferences putLong(String key, long value) {
        properties.put(key, Long.toString(value));
        return this;
    }

    @Override
    public Preferences putFloat(String key, float value) {
        properties.put(key, Float.toString(value));
        return this;
    }

    @Override
    public Preferences putString(String key, String value) {
        properties.put(key, value);
        return this;
    }

    @Override
    public Preferences put(Map<String, ?> values) {
        properties.putAll(values);
        return this;
    }

    @Override
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    @Override
    public int getInteger(String key) {
        return getInteger(key, 0);
    }

    @Override
    public long getLong(String key) {
        return getLong(key, 0);
    }

    @Override
    public float getFloat(String key) {
        return getFloat(key, 0);
    }

    @Override
    public String getString(String key) {
        return getString(key, "");
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return (boolean)properties.getOrDefault(key, defValue);
    }

    @Override
    public int getInteger(String key, int defValue) {
        return (int)properties.getOrDefault(key, defValue);
    }

    @Override
    public long getLong(String key, long defValue) {
        return (long)properties.getOrDefault(key, defValue);
    }

    @Override
    public float getFloat(String key, float defValue) {
        return (float)properties.getOrDefault(key, defValue);
    }

    @Override
    public String getString(String key, String defValue) {
        return (String)properties.getOrDefault(key, defValue);
    }

    @Override
    public Map<String, ?> get() {
        return properties;
    }

    @Override
    public boolean contains(String key) {
        return properties.containsKey(key);
    }

    @Override
    public void clear() {
        properties.clear();
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(String key) {
        properties.remove(key);
    }
}
