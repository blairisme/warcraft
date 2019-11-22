/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.state.StateIdentifier;

import static com.evilbird.engine.common.file.FileType.JSON;

/**
 * An identifier for a custom scenarios.
 *
 * @author Blair Butterworth
 */
public enum WarcraftScenario implements StateIdentifier
{
    Silverpine;

    public String getRoot() {
        return "scenario";
    }

    public String getFile() {
        String name = name().toLowerCase();
        String extension = JSON.getFileExtension();
        return name + extension;
    }
}
