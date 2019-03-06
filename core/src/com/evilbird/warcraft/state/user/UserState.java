/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.user;

import com.evilbird.warcraft.state.StateIdentifier;
import org.apache.commons.io.FilenameUtils;

public class UserState implements StateIdentifier
{
    private String path;
    private String name;

    public UserState(String path) {
        this.path = path;
        this.name = FilenameUtils.getBaseName(path);
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return name;
    }
}
