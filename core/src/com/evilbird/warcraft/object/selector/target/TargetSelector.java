/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.selector.target;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.warcraft.object.selector.Selector;

/**
 * Represents a visual guide used to select game world locations for use by
 * actions.
 *
 * @author Blair Butterworth
 */
public class TargetSelector extends Selector
{
    private static final transient float SPEED = 10f;

    private transient TextureRegion region;
    private transient Vector2 center;
    private transient float rotation;

    public TargetSelector() {
        center = Vector2.Zero;
        rotation = 0;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        Vector2 size = getSize();
        Vector2 position = getPosition();

        SpriteBatch spriteBatch = (SpriteBatch)batch;
        spriteBatch.draw(region, position.x, position.y, center.x, center.y, size.x, size.y, 1f, 1f, rotation, true);
    }

    public void setTexture(Texture texture) {
        this.region = new TextureRegion(texture);
    }

    @Override
    public void setSize(Vector2 size) {
        super.setSize(size);
        center = size.cpy();
        center.scl(0.5f);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        center = new Vector2(width, height);
        center.scl(0.5f);
    }

    @Override
    public void update(float time) {
        super.update(time);
        rotation = rotation - (SPEED * time);
        if (rotation < 0) {
            rotation += 360;
        }
    }
}
