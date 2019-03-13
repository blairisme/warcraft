/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.world;

public enum WorldStateIdentifier
{
    Human1 ("data/levels/human/level1.tmx");

    private String assetPath;

    WorldStateIdentifier(String assetPath) {
        this.assetPath = assetPath;
    }

    public String getFilePath() {
        return assetPath;
    }
}
