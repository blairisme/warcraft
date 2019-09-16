/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.preferences;

/**
 * Implementors of this interface are called when {@link GamePreferences} are
 * saved.
 *
 * @author Blair Butterworth
 */
public interface GamePreferencesObserver
{
    void onPreferencesSaved(GamePreferences preferences);
}
