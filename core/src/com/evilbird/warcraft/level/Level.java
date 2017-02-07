package com.evilbird.warcraft.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.evilbird.warcraft.GameScene;
import com.evilbird.warcraft.device.Device;
import com.evilbird.warcraft.device.UserInput;
import com.evilbird.warcraft.interaction.InteractionAnalyzer;

import java.util.List;

public class Level extends GameScene
{
    private Stage hud;
    private Stage world;
    private InteractionAnalyzer interactionAnalyzer;

    public Level(Stage world, Stage hud, InteractionAnalyzer interactionAnalyzer)
    {
        this.world = world;
        this.hud = hud;
        this.interactionAnalyzer = interactionAnalyzer;
    }

    @Override
    public void setDevice(Device device)
    {
        super.setDevice(device);
        device.getDeviceInput().install(); //TODO: Do better
    }

    @Override
    public void render(float delta)
    {
        world.getCamera().update();

        List<UserInput> inputs = getDevice().getDeviceInput().readInput();
        interactionAnalyzer.update(world, inputs);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.act(delta);
        world.draw();

        hud.act(delta);
        hud.draw();
    }

    @Override
    public void resize(int width, int height)
    {
        world.getViewport().update(width, height);
        hud.getViewport().update(width, height);
    }

    @Override
    public void dispose()
    {
        world.dispose();
        hud.dispose();
    }
}
