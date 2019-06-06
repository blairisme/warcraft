/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.state.StateIdentifier;

/**
 * Uniquely identifiers a state in the warcraft game.
 *
 * @author Blair Butterworth
 */
public class WarcraftStateIdentifier implements StateIdentifier
{
    private String name;

    public WarcraftStateIdentifier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}