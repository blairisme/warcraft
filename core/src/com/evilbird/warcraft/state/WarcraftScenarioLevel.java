/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
