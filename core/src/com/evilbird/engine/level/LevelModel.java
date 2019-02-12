/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.level;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.behaviour.BehaviourIdentifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.event.Events;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.StateFactory;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.warcraft.state.campaign.human.HumanCampaign;
import com.evilbird.warcraft.state.hud.HudType;

import javax.inject.Inject;

public class LevelModel
{
    private Level presenter;
    private Device device;

    private StateFactory stateFactory;
    private BehaviourFactory behaviourFactory;

    private ItemRoot hud;
    private ItemRoot world;
    private Behaviour behaviour;
    private Events events;

    @Inject
    public LevelModel(
        Device device,
        StateFactory stateFactory,
        BehaviourFactory behaviourFactory,
        Events events)
    {
        this.device = device;
        this.stateFactory = stateFactory;
        this.behaviourFactory = behaviourFactory;
        this.events = events;
    }

    public void setPresenter(Level presenter) {
        this.presenter = presenter;
    }

    public void load(StateIdentifier worldId, StateIdentifier hudId, BehaviourIdentifier behaviourId) {
        world = stateFactory.get(worldId);
        hud = stateFactory.get(hudId);
        behaviour = behaviourFactory.newBehaviour(behaviourId);

        presenter.setHud(hud);
        presenter.setWorld(world);
        device.getDeviceInput().install();
    }

    public void update(float delta) {
        behaviour.update(world, hud, device.getDeviceInput().readInput());
        events.clear();
        world.update(delta);
        hud.update(delta);
    }
}
