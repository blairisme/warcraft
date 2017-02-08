package com.evilbird.warcraft.level;

import com.evilbird.warcraft.GameService;
import com.evilbird.warcraft.device.Device;
import com.evilbird.warcraft.device.UserInput;
import com.evilbird.warcraft.hud.Hud;
import com.evilbird.warcraft.interaction.Behaviour;
import com.evilbird.warcraft.unit.World;

import java.util.List;

public class LevelModel
{
    private Level presenter;
    private Device device;
    private GameService service;

    private Hud hud;
    private World world;
    private Behaviour behaviour;

    public LevelModel(Level presenter, Device device, GameService service)
    {
        this.device = device;
        this.presenter = presenter;
        this.service = service;
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
