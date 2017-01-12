package com.evilbird.warcraft;

import com.badlogic.gdx.Game;
import com.evilbird.warcraft.device.Device;
import com.evilbird.warcraft.menu.MenuScreen;

public class GameEngine extends Game implements GameView
{
    private Device device;

    public GameEngine(Device device)
    {
        this.device = device;
    }

    @Override
    public void create()
    {
        setScreen(new MenuScreen(this, device));
    }
}
