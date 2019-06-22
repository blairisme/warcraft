/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.campaign;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.state.scenario.WarcraftScenario;

public class WarcraftCampaign extends WarcraftScenario
{
    public WarcraftCampaign(ItemRoot world, ItemRoot hud, Behaviour behaviour) {
        super(world, hud, behaviour);
    }
}
