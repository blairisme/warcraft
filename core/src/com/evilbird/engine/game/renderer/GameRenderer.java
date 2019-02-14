/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.game.renderer;

import com.badlogic.gdx.ScreenAdapter;
import com.evilbird.engine.state.State;

import javax.inject.Inject;

public class GameRenderer extends ScreenAdapter
{
    private GameRendererView view;
    private GameRendererModel model;

    @Inject
    public GameRenderer(GameRendererModel model, GameRendererView view) {
        this.view = view;
        this.model = model;
    }

    public void setState(State state) {
        view.setState(state);
        model.setState(state);
        model.initialize();
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
}
