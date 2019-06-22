/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.common.file.FileType;

import java.io.File;

/**
 * Uniquely identifiers a state in the warcraft game using a user provided
 * name. Used to save game state.
 *
 * @author Blair Butterworth
 */
public class WarcraftSave implements WarcraftStateIdentifier
{
    private static final String SAVES = "saves";
    private static final String JSON = FileType.JSON.getFileExtension();

    private String name;

    public WarcraftSave(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getFilePath() {
        return SAVES + File.separator + name + JSON;
    }
}