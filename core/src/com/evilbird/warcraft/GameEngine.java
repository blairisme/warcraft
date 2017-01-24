package com.evilbird.warcraft;

import com.badlogic.gdx.Game;
import com.evilbird.warcraft.device.Device;
import com.evilbird.warcraft.loader.GameLoader;

public class GameEngine extends Game
{
    private Device device;

    public GameEngine(Device device)
    {
        this.device = device;
    }

    @Override
    public void create()
    {
        GameLoader gameLoader = new GameLoader(device, this);
        setScreen(gameLoader);
    }
}
