package com.evilbird.engine.level;

import com.badlogic.gdx.ScreenAdapter;
import com.evilbird.engine.item.ItemRoot;

import javax.inject.Inject;

public class Level extends ScreenAdapter
{
    private LevelView view;
    private LevelModel model;

    @Inject
    public Level(LevelModel model, LevelView view)
    {
        this.view = view;
        this.model = model;
        this.model.setPresenter(this);
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

    void setWorld(ItemRoot world)
    {
        view.setWorld(world);
    }

    void setHud(ItemRoot hud)
    {
        view.setHud(hud);
    }
}
