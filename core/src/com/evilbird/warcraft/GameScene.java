package com.evilbird.warcraft;

import com.badlogic.gdx.ScreenAdapter;
import com.evilbird.warcraft.device.Device;

public abstract class GameScene extends ScreenAdapter
{
    private GameView view;
    private Device device;

    public GameScene(GameView view, Device device)
    {
        this.view = view;
        this.device = device;
        create();
    }

    public abstract void create();

    public GameView getView()
    {
        return view;
    }

    public Device getDevice()
    {
        return device;
    }
}
