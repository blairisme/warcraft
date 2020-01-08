/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object.interop;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.evilbird.engine.object.GameObject;

/**
 * Instances of this class decorate the LibGDX {@link Button} class with an
 * {@link GameObject}, allowing the Item to receive events from the Button without
 * inheriting from it.
 *
 * @author Blair Butterworth
 */
public class ButtonDecorator extends Button
{
    private GameObject gameObject;

    public ButtonDecorator(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        gameObject.update(delta);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        gameObject.draw(batch, alpha);
        super.draw(batch, alpha);
    }

    @Override
    public void positionChanged() {
        gameObject.positionChanged();
    }

    @Override
    public void sizeChanged() {
        gameObject.sizeChanged();
    }
}
