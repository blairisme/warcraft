/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.serialization.SerializedType;

/**
 * Defines states shipped in the application bundle. I.e., built in levels and
 * scenarios.
 *
 * @author Blair Butterworth
 */
@SerializedType("Scenario")
public enum WarcraftStateAsset implements Identifier
{
    Level1("data/levels/human/level1.tmx");

    private String path;

    WarcraftStateAsset(String path) {
        this.path = path;
    }

    public String getFilePath() {
        return path;
    }
}
