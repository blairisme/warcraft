/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.game.error;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.evilbird.engine.common.graphics.Colours;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.engine.common.text.Fonts;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;

import javax.inject.Inject;

/**
 * Represents a user interface view shown if an unexpected errors during the
 * operation of the application.
 *
 * @author Blair Butterworth
 */
public class ErrorScreen extends ScreenAdapter
{
    private Stage stage;
    private DeviceDisplay display;

    @Inject
    public ErrorScreen(Device device) {
        display = device.getDeviceDisplay();
    }

    public void show() {
        stage = new Stage();
        stage.addActor(createContainer());
        stage.setViewport(createViewport());
    }

    public void setError(Throwable error) {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        Gdx.graphics.setContinuousRendering(false);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private Viewport createViewport() {
        ScreenViewport viewport = new ScreenViewport();
        viewport.setUnitsPerPixel(display.getPixelUnits());
        return viewport;
    }

    private Table createContainer() {
        Table container = new Table();
        container.setFillParent(true);
        container.setBackground(TextureUtils.getDrawable(Colours.GRAPE));
        container.center();

        Label label = createLabel("Unfortunately something when wrong");
        Cell cell = container.add(label);
        cell.expandX();

        return container;
    }

    private Label createLabel(String text) {
        LabelStyle style = new LabelStyle();
        style.font = Fonts.ARIAL;
        style.fontColor = Color.WHITE;

        Label label = new Label(text, style);
        label.setAlignment(Align.center);
        label.setWrap(true);

        return label;
    }
}
