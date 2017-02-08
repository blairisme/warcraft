package com.evilbird.warcraft.level;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.evilbird.warcraft.GameScene;
import com.evilbird.warcraft.GameService;
import com.evilbird.warcraft.device.Device;

public class Level extends GameScene
{
    private LevelView view;
    private LevelModel model;

    public Level(Device device, GameService service)
    {
        this.view = new LevelView();
        this.model = new LevelModel(this, device, service);
        this.model.load();
    }

    @Override
    public void render(float delta)
    {
        model.update(delta);
        view.draw();
    }

    @Override
    public void resize(int width, int height)
    {
        view.resize(width, height);
    }

    @Override
    public void dispose()
    {
        view.dispose();
    }

    void setWorld(Stage world)
    {
        view.setWorld(world);
    }

    void setHud(Stage hud)
    {
        view.setHud(hud);
    }
}
