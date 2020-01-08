/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.badge;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.object.BasicGameObject;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectReference;

import javax.inject.Inject;
import java.util.Objects;

/**
 * A visual icon applied to a game object to denote a change in its state.
 * Badges icons are rendered in the top right hand corner of the size assigned
 * to the badge object.
 *
 * @author Blair Butterworth
 */
public class Badge extends BasicGameObject
{
    private GameObjectReference target;

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

    public void setIcon(Drawable badge) {
        Objects.requireNonNull(badge);
        this.badge = badge;
        this.badgeSize = new Vector2(16, 16);
        this.badgeOffset = new Vector2(8, 8);
        updateBadgePosition();
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

    public void setTarget(GameObject target) {
        this.target = new GameObjectReference<>(target);
        setPosition(target.getPosition());
        setSize(target.getSize());
    }

    @Override
    public void update(float time) {
        super.update(time);

        if (target != null) {
            GameObject host = target.get();
            Vector2 position = host.getPosition();

            if (position != this.position) {
                setPosition(position);
            }
        }
    }

    private void updateBadgePosition() {
        this.badgePosition = new Vector2(
            position.x + size.x - badgeOffset.x,
            position.y + size.y - badgeOffset.y);
    }
}
