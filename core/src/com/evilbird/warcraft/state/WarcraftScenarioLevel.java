/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.state;

import static com.evilbird.engine.common.file.FileType.TMX;

/**
 * Defines scenario level files: tiled maps containing terrain and player
 * content.
 *
 * @author Blair Butterworth
 */
public enum WarcraftScenarioLevel implements Level
{
    Crossroads,
    Silverpine;

    @Override
    public String getFilePath() {
        return "data/levels/scenario/" + name() + TMX.getFileExtension();
    }
}
