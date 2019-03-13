/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.asset;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.state.WarcraftState;
import com.evilbird.warcraft.state.hud.HudStateFactory;
import com.evilbird.warcraft.state.world.WorldStateFactory;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class AssetStateService
{
    private HudStateFactory hudFactory;
    private WorldStateFactory worldFactory;
    private BehaviourFactory behaviourFactory;

    @Inject
    public AssetStateService(
        WorldStateFactory worldFactory,
        HudStateFactory hudFactory,
        BehaviourFactory behaviourFactory)
    {
        this.worldFactory = worldFactory;
        this.hudFactory = hudFactory;
        this.behaviourFactory = behaviourFactory;
    }

    public List<Identifier> list() {
        return Arrays.asList(AssetState.values());
    }

    public State get(AssetState assetState) {
        ItemRoot map = worldFactory.get(assetState.getWorld());
        ItemRoot hud = hudFactory.get(assetState.getHud());
        Behaviour behaviour = behaviourFactory.newBehaviour(null);
        return new WarcraftState(map, hud, behaviour);
    }
}
