/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.map;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.serialization.SerializedType;

/**
 * Defines states shipped in the application bundle. I.e., built in levels and
 * scenarios.
 *
 * @author Blair Butterworth
 */
@SerializedType("Scenario")
public enum WarcraftLevel implements Identifier
{
    Human1("data/levels/human/level1.tmx"),
    Human2("data/levels/human/level2.tmx");

    private String path;

    WarcraftLevel(String path) {
        this.path = path;
    }

    public String getFilePath() {
        return path;
    }
}
