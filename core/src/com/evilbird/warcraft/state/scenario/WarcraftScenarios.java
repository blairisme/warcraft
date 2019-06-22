/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.scenario;

import com.evilbird.engine.state.StateIdentifier;

public enum WarcraftScenarios implements StateIdentifier
{
    Silverwood("data/levels/common/silverwood.json");

    private String path;

    WarcraftScenarios(String path) {
        this.path = path;
    }

    public String getFilePath() {
        return path;
    }
}
