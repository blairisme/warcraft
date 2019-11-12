/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.object.interop;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.evilbird.engine.object.GameObjectGroup;

/**
 * Instances of this class decorate the LibGDX {@link Group} class with an
 * {@link GameObjectGroup}, allowing the ItemGroup to receive events from the Group
 * without inheriting from it.
 *
 * @author Blair Butterworth
 */
public class GroupDecorator extends Group
{
    private GameObjectGroup objectGroup;

    public GroupDecorator(GameObjectGroup objectGroup) {
        this.objectGroup = objectGroup;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        objectGroup.update(delta);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        objectGroup.draw(batch, alpha);
        super.draw(batch, alpha);
    }

    @Override
    protected void drawChildren(Batch batch, float parentAlpha) {
        objectGroup.drawChildrenBegin(batch, parentAlpha);
        super.drawChildren(batch, parentAlpha);
        objectGroup.drawChildrenEnd(batch, parentAlpha);
    }

    @Override
    public void positionChanged() {
        objectGroup.positionChanged();
    }

    @Override
    public void sizeChanged() {
        objectGroup.sizeChanged();
    }
}
