/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.game.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import javax.inject.Inject;

/**
 * Renders a loading image to the screen while the {@link LoaderScreenModel}
 * initializes the various factories and services that provide game objects and
 * behaviour.
 *
 * @author Blair Butterworth
 */
public class LoaderScreenView
{
    private Stage stage;
    private Table container;

    @Inject
    public LoaderScreenView() {
    }

    public void show() {
        container = new Table();
        container.setFillParent(true);

        stage = new Stage();
        stage.addActor(container);
    }

    public void setBackground(Texture texture) {
        TextureRegion region = new TextureRegion(texture);
        Drawable drawable = new TextureRegionDrawable(region);
        container.setBackground(drawable);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    public void dispose() {
        if (stage != null) {
            stage.dispose();
        }
    }
}
