/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceStorage;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.State;
import com.evilbird.engine.state.StateService;
import com.evilbird.warcraft.state.hud.HudFactory;
import com.evilbird.warcraft.state.map.MapFactory;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class WarcraftStateService implements StateService
{
    private static String SAVE_PATH = ""; //TODO

    private Device device;
    private HudFactory hudFactory;
    private MapFactory mapFactory;
    private BehaviourFactory behaviourFactory;

    @Inject
    public WarcraftStateService(
        Device device,
        MapFactory mapFactory,
        HudFactory hudFactory,
        BehaviourFactory behaviourFactory)
    {
        this.device = device;
        this.mapFactory = mapFactory;
        this.hudFactory = hudFactory;
        this.behaviourFactory = behaviourFactory;
    }

    @Override
    public void load() {
    }

    @Override
    public List<Identifier> list(Identifier category) {
        Validate.isInstanceOf(StateType.class, category);
        switch ((StateType)category) {
            case Campaign: return asList(Campaign.values());
            case Scenario: return asList(Scenario.values());
            case UserSave: return getUserSaves();
            default: throw new UnsupportedOperationException();
        }
    }

    private List<Identifier> getUserSaves() {
//        try {
//            DeviceStorage storage = device.getInternalStorage();
//            List<String> persisted = storage.list(SAVE_PATH);
//            return persisted.stream().map(UserSave::new).collect(Collectors.toList());
//        }
//        catch (IOException exception) {
//            System.out.println(exception.getMessage()); //TODO: handle exception properly
//            return Collections.emptyList();
//        }
        return Collections.emptyList();
    }

    @Override
    public State get(Identifier identifier) {
        Validate.isInstanceOf(StateDefinition.class, identifier);
        StateDefinition definition = (StateDefinition)identifier;

        ItemRoot map = mapFactory.get(definition.getMap());
        ItemRoot hud = hudFactory.get(definition.getHud());
        Behaviour behaviour = behaviourFactory.newBehaviour(null); //TODO

        return new State(map, hud, behaviour);
    }

    @Override
    public void set(Identifier identifier, State state) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public void remove(Identifier identifier) {
        throw new UnsupportedOperationException(); //TODO
    }
}
