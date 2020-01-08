/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
    Crossroads,
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
