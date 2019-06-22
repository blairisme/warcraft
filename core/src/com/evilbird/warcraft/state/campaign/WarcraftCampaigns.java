/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.campaign;

import com.evilbird.engine.state.StateIdentifier;

/**
 * Defines states shipped in the application bundle. I.e., built in levels and
 * scenarios.
 *
 * @author Blair Butterworth
 */
public enum WarcraftCampaigns implements StateIdentifier
{
    Human1("data/levels/human/level1.json"),
    Human2("data/levels/human/level2.json"),
    Orc1("data/levels/orc/level1.json"),
    Orc2("data/levels/orc/level2.json");

    private String path;

    WarcraftCampaigns(String path) {
        this.path = path;
    }

    public String getFilePath() {
        return path;
    }
}
