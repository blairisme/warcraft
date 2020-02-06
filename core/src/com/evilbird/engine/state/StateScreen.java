/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.evilbird.engine.audio.AudioManager;
import com.evilbird.engine.audio.music.Music;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceInput;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.object.GameObjectContainer;

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
    private GameObjectContainer world;
    private GameObjectContainer hud;
    private Behaviour behaviour;
    private AudioManager audioManager;
    private GameController controller;

    @Inject
    public StateScreen(Device device, EventQueue events, AudioManager audioManager) {
        this.input = device.getDeviceInput();
        this.events = events;
        this.audioManager = audioManager;
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

        Music music = state.getMusic();
        if (!audioManager.isPlaying(music)) {
            audioManager.stop();
            audioManager.play(music);
        }
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

    public void update(float time){
        behaviour.update(state, input.getInput(), time);
        events.update();
        world.update(time);
        hud.update(time);
    }

    public void draw() {
        world.draw();
        hud.draw();
    }
}
