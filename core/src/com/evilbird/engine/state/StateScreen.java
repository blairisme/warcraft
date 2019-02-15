/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceInput;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.item.ItemRoot;

import javax.inject.Inject;

public class StateScreen extends ScreenAdapter
{
    private DeviceInput input;
    private EventQueue events;
    private State state;
    private ItemRoot world;
    private ItemRoot hud;
    private Behaviour behaviour;
    private GameController controller;

    @Inject
    public StateScreen(Device device, EventQueue events) {
        this.input = device.getDeviceInput();
        this.events = events;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        this.world = state.getWorld();
        this.hud = state.getHud();
        this.behaviour = state.getBehaviour();
        updateController();
    }

    public void setController(GameController controller) {
        this.controller = controller;
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
        world.dispose();
        hud.dispose();
    }

    @Override
    public void show() {
        input.install();
    }

    public void resize(int width, int height) {
        world.resize(width, height);
        hud.resize(width, height);
    }

    @Override
    public void render(float delta) {
        update(delta);
        render();
    }

    private void update(float delta){
        behaviour.update(state, input.readInput());
        events.clear();
        world.update(delta);
        hud.update(delta);
    }

    private void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.draw();
        hud.draw();
    }
}
