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
 * Implementors of this interface provide methods that are called when an Actor
 * is drawn or updated or when its size or position is altered.
 *
 * @author Blair Butterworth
 */
public interface ActorObserver
{
    void draw(Batch batch, float alpha);

    /** Updates the actor based on time.
     *
     * @param delta Time in seconds since the last frame.
     */
    void update(float delta);

    /**
     * Called when the actor's position has been changed.
     */
    void positionChanged();

    /**
     * Called when the actor's size has been changed.
     */
    void sizeChanged();
}
