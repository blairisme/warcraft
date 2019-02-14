/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.State;
import com.evilbird.engine.state.StateFactory;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.warcraft.state.map.MapFactory;
import com.evilbird.warcraft.state.hud.HudFactory;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

public class WarcraftStateFactory implements StateFactory
{
    private HudFactory hudFactory;
    private MapFactory mapFactory;

    @Inject
    public WarcraftStateFactory(MapFactory mapFactory, HudFactory hudFactory) {
        this.mapFactory = mapFactory;
        this.hudFactory = hudFactory;
    }

    @Override
    public void load() {
        hudFactory.load();
        mapFactory.load();
    }

    @Override
    public State get(StateIdentifier identifier) {
        Validate.isInstanceOf(StateDefinition.class, identifier);
        StateDefinition definition = (StateDefinition)identifier;

        ItemRoot map = mapFactory.get(definition.getMap());
        ItemRoot hud = hudFactory.get(definition.getHud());

        return new State(map, hud);
    }
}
