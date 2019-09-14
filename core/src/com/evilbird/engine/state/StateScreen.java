/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.common.audio.music.MusicService;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceInput;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.item.ItemRoot;

import javax.inject.Inject;

/**
 * Updates and draws a {@link State game state} to the screen.
 *
 * @author Blair Butterworth
 */
public class StateScreen extends ScreenAdapter
{
    private DeviceInput input;
    private EventQueue events;
    private State state;
    private StateIdentifier identifier;
    private ItemRoot world;
    private ItemRoot hud;
    private Behaviour behaviour;
    private MusicService music;
    private GameController controller;

    @Inject
    public StateScreen(Device device, EventQueue events, MusicService music) {
        this.input = device.getDeviceInput();
        this.events = events;
        this.music = music;
    }

    public State getState() {
        return state;
    }

    public StateIdentifier getIdentifier() {
        return identifier;
    }

    public boolean hasState() {
        return state != null;
    }

    public void setController(GameController controller) {
        this.controller = controller;
        updateController();
    }

    public void setState(State state, StateIdentifier identifier) {
        this.state = state;
        this.identifier = identifier;
        this.world = state.getWorld();
        this.hud = state.getHud();
        this.behaviour = state.getBehaviour();
        updateController();
    }

    private void updateController() {
        if (state != null && controller != null) {
            world.setController(controller);
            hud.setController(controller);
        }
    }

    @Override
    public void dispose() {
        if (state != null) {
            state.dispose();
        }
    }

    @Override
    public void show() {
        input.startMonitoring();
        music.play(state.getMusic());
    }

    public void resize(int width, int height) {
        world.resize(width, height, false);
        hud.resize(width, height, true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);
        draw();
    }

    public void update(float delta){
        behaviour.update(state, input.getInput());
        events.clear();
        world.update(delta);
        hud.update(delta);
    }

    public void draw() {
        world.draw();
        hud.draw();
    }
}
