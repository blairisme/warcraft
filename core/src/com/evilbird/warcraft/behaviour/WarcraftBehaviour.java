/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour;

import com.evilbird.engine.behaviour.BehaviourIdentifier;
import com.evilbird.engine.common.collection.EnumUtils;

/**
 * Defines identifiers for behaviour varieties.
 *
 * @author Blair Butterworth
 */
public enum WarcraftBehaviour implements BehaviourIdentifier
{
    /**
     * Defines behaviour appropriate to human campaign levels.
     */
    Human1,
    Human2,
    Human3,
    Human4,
    Human5,
    Human6,
    Human7,
    Human8,
    Human9,
    Human10,
    Human11,
    Human12,
    Human13,
    Human14,

    /**
     * Defines behaviour appropriate to human campaign levels.
     */
    Orc1,
    Orc2,
    Orc3,
    Orc4,
    Orc5,
    Orc6,
    Orc7,
    Orc8,
    Orc9,
    Orc10,
    Orc11,
    Orc12,
    Orc13,
    Orc14,

    ScenarioEasy,
    ScenarioMedium,
    ScenarioHard;

    public boolean isHumanCampaign() {
        return EnumUtils.isBetween(this, Human1, Human14);
    }

    public boolean isOrcCampaign() {
        return EnumUtils.isBetween(this, Orc1, Orc14);
    }

    public boolean isScenario() {
        return EnumUtils.isBetween(this, ScenarioEasy, ScenarioHard);
    }
}
