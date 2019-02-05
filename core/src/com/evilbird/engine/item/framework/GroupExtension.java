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
import com.badlogic.gdx.scenes.scene2d.Group;

public class GroupExtension extends Group
{
    private GroupObserver observer;

    public GroupExtension(GroupObserver observer) {
        this.observer = observer;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        observer.update(delta);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        observer.draw(batch, alpha);
        super.draw(batch, alpha);

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
