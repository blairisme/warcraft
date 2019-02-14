/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.map;

import com.evilbird.engine.common.lang.Identifier;

public enum Map implements Identifier, MapDefinition
{
    Human1 ("data/levels/human/level1.tmx");

    private String assetPath;

    Map(String assetPath) {
        this.assetPath = assetPath;
    }

    @Override
    public String getAssetPath() {
        return assetPath;
    }
}
