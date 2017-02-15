package com.evilbird.engine.level;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.hud.Hud;
import com.evilbird.engine.hud.HudFactory;
import com.evilbird.engine.world.World;
import com.evilbird.engine.world.WorldFactory;

import java.util.List;

import javax.inject.Inject;

public class LevelModel
{
    private Level presenter;
    private Device device;

    private HudFactory hudFactory;
    private WorldFactory worldFactory;
    private BehaviourFactory behaviourFactory;

    private Hud hud;
    private World world;
    private Behaviour behaviour;

    @Inject
    public LevelModel(
        Device device,
        HudFactory hudFactory,
        WorldFactory worldFactory,
        BehaviourFactory behaviourFactory)
    {
        this.device = device;
        this.hudFactory = hudFactory;
        this.worldFactory = worldFactory;
        this.behaviourFactory = behaviourFactory;
    }

    public void setPresenter(Level presenter)
    {
        this.presenter = presenter;
    }

    public void load()
    {
        world = worldFactory.newWorld(null); //TODO
        hud = hudFactory.newHud(null); //TODO
        behaviour = behaviourFactory.newBehaviour(null); //TODO

        presenter.setHud(hud);
        presenter.setWorld(world);

        device.getDeviceInput().install(); //TODO
    }

    public void update(float delta)
    {
        List<UserInput> inputs = device.getDeviceInput().readInput();
        behaviour.update(world, hud, inputs);

        world.getCamera().update();
        world.act(delta);

        hud.getCamera().update();
        hud.act(delta);
    }
}
