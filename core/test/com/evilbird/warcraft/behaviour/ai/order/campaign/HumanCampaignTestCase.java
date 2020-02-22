/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.order.campaign;

import com.evilbird.warcraft.common.WarcraftFaction;

/**
 * A test suite that validates logic in human campaign ai operation order
 * classes.
 *
 * @author Blair Butterworth
 */
public abstract class HumanCampaignTestCase extends CampaignTestCase
{
    @Override
    public WarcraftFaction getFaction() {
        return WarcraftFaction.Orc;
    }
}
