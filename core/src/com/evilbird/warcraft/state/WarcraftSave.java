/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.badlogic.gdx.files.FileHandle;
import com.evilbird.engine.game.GameState;
import com.evilbird.engine.state.StateIdentifier;

import static com.evilbird.engine.common.file.FileType.JSON;
import static com.evilbird.engine.common.text.CaseUtils.toSnakeCase;
import static org.apache.commons.io.FilenameUtils.getBaseName;

/**
 * Uniquely identifiers a state in the warcraft game using a user provided
 * name. Used to save game state.
 *
 * @author Blair Butterworth
 */
public class WarcraftSave implements StateIdentifier
{
    private String name;
    private String file;

    public WarcraftSave(FileHandle handle) {
        this(handle.name());
    }

    public WarcraftSave(String path) {
        this.name = getBaseName(path);
        this.file = name + JSON.getFileExtension();
    }

    public WarcraftSave(GameState state) {
        this.name = toSnakeCase(state.name());
        this.file = name + JSON.getFileExtension();
    }

    public String getName() {
        return name;
    }

    public String getFileName() {
        return file;
    }

    @Override
    public String toString() {
        return name;
    }

    public static String getExtension() {
        return JSON.getExtension();
    }
}