/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.game.renderer;

import com.evilbird.engine.action.events.EventQueue;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceInput;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.State;

import javax.inject.Inject;

public class GameRendererModel
{
    private DeviceInput input;
    private EventQueue events;
    private State state;
    private ItemRoot world;
    private ItemRoot hud;
    private Behaviour behaviour;

    @Inject
    public GameRendererModel(Device device, EventQueue events) {
        this.input = device.getDeviceInput();
        this.events = events;
    }

    public void setState(State state) {
        this.state = state;
        this.world = state.getWorld();
        this.hud = state.getHud();
        this.behaviour = state.getBehaviour();
    }

    public void initialize() {
        input.install();
    }

    public void update(float delta) {
        behaviour.update(state, input.readInput());
        events.clear();
        world.update(delta);
        hud.update(delta);
    }
}
