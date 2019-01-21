/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.framework;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Instances of this class extend the LibGDX Actor class, providing the ability
 * observe invocation of act and draw. This allows sub classes to supplement
 * this logic.
 *
 * @author Blair Butterworth
 */
public class ActorExtension extends com.badlogic.gdx.scenes.scene2d.Actor
{
    private ActorObserver observer;

    public ActorExtension(ActorObserver observer) {
        this.observer = observer;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        observer.update(delta);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        super.draw(batch, alpha);
        observer.draw(batch, alpha);
    }

    @Override
    public void positionChanged() {
        observer.positionChanged();
    }

    @Override
    public void sizeChanged() {
        observer.sizeChanged();
    }
}
