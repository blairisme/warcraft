package com.evilbird.warcraft;

import com.badlogic.gdx.ScreenAdapter;
import com.evilbird.warcraft.device.Device;

public abstract class GameScene extends ScreenAdapter
{
    private Device device;
    private GameEngine engine;
    private GameService service;

    public GameScene()
    {
        this.engine = null;
        this.device = null;
        this.service = null;
    }

    public GameScene(Device device, GameEngine engine, GameService service)
    {
        this.device = device;
        this.engine = engine;
        this.service = service;
    }

    public Device getDevice()
    {
        return device;
    }

    public GameEngine getEngine()
    {
        return engine;
    }

    public GameService getService()
    {
        return service;
    }

    public void setDevice(Device device)
    {
        this.device = device;
    }

    public void setEngine(GameEngine engine)
    {
        this.engine = engine;
    }

    public void setService(GameService service)
    {
        this.service = service;
    }

    public void setScreen(GameScene screen)
    {
        screen.setDevice(device);
        screen.setEngine(engine);
        screen.setService(service);
        engine.setScreen(screen);
    }
}
