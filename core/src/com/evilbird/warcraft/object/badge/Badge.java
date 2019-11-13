/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.badge;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.graphics.Renderable;
import com.evilbird.engine.object.BasicGameObject;

import javax.inject.Inject;

/**
 * A visual icon applied to a game object to denote a change in its state.
 *
 * @author Blair Butterworth
 */
public class Badge extends BasicGameObject implements Renderable
{
    private transient Drawable badge;
    private transient Vector2 size;
    private transient Vector2 position;
    private transient Vector2 badgeSize;
    private transient Vector2 badgePosition;
    private transient Vector2 badgeOffset;

    @Inject
    public Badge() {
        size = getSize();
        position = getPosition();
    }

    @Override
    public void draw(Batch batch, float alpha) {
        badge.draw(batch, badgePosition.x, badgePosition.y, badgeSize.x, badgeSize.y);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        this.position.set(x, y);
        updateBadgePosition();
    }

    @Override
    public void setPosition(Vector2 position) {
        super.setPosition(position);
        this.position.set(position);
        updateBadgePosition();
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        this.size.set(width, height);
        updateBadgePosition();
    }

    @Override
    public void setSize(Vector2 size) {
        super.setSize(size);
        this.size.set(size);
        updateBadgePosition();
    }

    public void setIcon(Drawable badge) {
        this.badge = badge;
        this.badgeSize = new Vector2(16, 16);
        this.badgeOffset = new Vector2(8, 8);
        updateBadgePosition();
    }

    private void updateBadgePosition() {
        this.badgePosition = new Vector2(
            position.x + size.x - badgeOffset.x,
            position.y + size.y - badgeOffset.y);
    }
}
