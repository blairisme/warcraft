/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.level;

import com.badlogic.gdx.ScreenAdapter;
import com.evilbird.engine.behaviour.BehaviourIdentifier;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.StateIdentifier;

import javax.inject.Inject;

public class Level extends ScreenAdapter
{
    private LevelView view;
    private LevelModel model;

    @Inject
    public Level(LevelModel model, LevelView view) {
        this.view = view;
        this.model = model;
        this.model.setPresenter(this);
    }

    public void load(StateIdentifier world, StateIdentifier hud, BehaviourIdentifier behaviour) {
        this.model.load(world, hud, behaviour);
    }

    @Override
    public void render(float delta) {
        model.update(delta);
        view.draw();
    }

    @Override
    public void resize(int width, int height) {
        view.resize(width, height);
    }

    @Override
    public void dispose() {
        view.dispose();
    }

    void setWorld(ItemRoot world) {
        view.setWorld(world);
    }

    void setHud(ItemRoot hud) {
        view.setHud(hud);
    }
}
