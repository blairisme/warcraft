package com.evilbird.engine.level;

import com.evilbird.engine.GameService;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.hud.Hud;
import com.evilbird.engine.world.World;

import java.util.List;

import javax.inject.Inject;

public class LevelModel
{
    private Level presenter;
    private Device device;
    private GameService service;

    private Hud hud;
    private World world;
    private Behaviour behaviour;

    @Inject
    public LevelModel(Device device, GameService service)
    {
        this.device = device;
        this.service = service;
    }

    public void setPresenter(Level presenter)
    {
        this.presenter = presenter;
    }

    public void load()
    {
        world = service.getWorldFactory().newWorld(null); //TODO
        hud = service.getHudFactory().newHud(null); //TODO
        behaviour = service.getBehaviourFactory().newBehaviour(null); //TODO

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
