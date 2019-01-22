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
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.StateFactory;
import com.evilbird.warcraft.state.campaign.human.HumanCampaign;
import com.evilbird.warcraft.state.hud.HudType;

import java.util.List;

import javax.inject.Inject;

//TODO: finish
public class LevelModel
{
    private Level presenter;
    private Device device;

    private StateFactory stateFactory;
    private BehaviourFactory behaviourFactory;

    private ItemRoot hud;
    private ItemRoot world;
    private Behaviour behaviour;

    @Inject
    public LevelModel(
        Device device,
        StateFactory stateFactory,
        BehaviourFactory behaviourFactory)
    {
        this.device = device;
        this.stateFactory = stateFactory;
        this.behaviourFactory = behaviourFactory;
    }

    public void setPresenter(Level presenter)
    {
        this.presenter = presenter;
    }

    public void load()
    {
        world = stateFactory.get(HumanCampaign.Level1); //TODO
        hud = stateFactory.get(HudType.Human);  //TODO
        behaviour = behaviourFactory.newBehaviour(null); //TODO Provide meaningful id

        presenter.setHud(hud);
        presenter.setWorld(world);

        device.getDeviceInput().install(); //TODO
    }

    public void update(float delta)
    {
        List<UserInput> inputs = device.getDeviceInput().readInput();
        behaviour.update(world, hud, inputs);
        world.update(delta);
        hud.update(delta);
    }
}
