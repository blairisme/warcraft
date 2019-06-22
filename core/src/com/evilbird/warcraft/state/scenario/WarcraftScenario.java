/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.scenario;

import com.evilbird.warcraft.state.WarcraftStateIdentifier;

/**
 * An identifier for a built-in scenario, that is standalone. I.E., not part of
 * a campaign.
 *
 * @author Blair Butterworth
 */
public enum WarcraftScenario implements WarcraftStateIdentifier
{
    Silverwood("data/levels/common/silverwood.json");

    private String path;

    WarcraftScenario(String path) {
        this.path = path;
    }

    public String getFilePath() {
        return path;
    }
}
