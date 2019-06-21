/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.interop;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.evilbird.engine.item.Item;

/**
 * Instances of this class decorate the LibGDX Actor class with an {@link Item},
 * allowing the item to receive events from the Actor without inheriting from
 * it.
 *
 * @author Blair Butterworth
 */
public class ActorDecorator extends Actor
{
    private Item item;

    public ActorDecorator(Item item) {
        this.item = item;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        item.update(delta);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        super.draw(batch, alpha);
        item.draw(batch, alpha);
    }

    @Override
    public void positionChanged() {
        item.positionChanged();
    }

    @Override
    public void sizeChanged() {
        item.sizeChanged();
    }
}
