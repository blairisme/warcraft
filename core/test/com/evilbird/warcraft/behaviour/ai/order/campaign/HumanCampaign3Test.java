/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.order.campaign;

import com.evilbird.warcraft.behaviour.ai.order.OperationOrder;

/**
 * Instances of this unit test validate the {@link HumanCampaign3} class.
 *
 * @author Blair Butterworth
 */
public class HumanCampaign3Test extends HumanCampaignTestCase
{
    @Override
    public OperationOrder getCampaign() {
        return new HumanCampaign3();
    }
}