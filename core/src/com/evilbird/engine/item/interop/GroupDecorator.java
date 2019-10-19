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
import com.badlogic.gdx.scenes.scene2d.Group;
import com.evilbird.engine.item.ItemGroup;

/**
 * Instances of this class decorate the LibGDX {@link Group} class with an
 * {@link ItemGroup}, allowing the ItemGroup to receive events from the Group
 * without inheriting from it.
 *
 * @author Blair Butterworth
 */
public class GroupDecorator extends Group
{
    private ItemGroup itemGroup;

    public GroupDecorator(ItemGroup itemGroup) {
        this.itemGroup = itemGroup;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        itemGroup.update(delta);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        itemGroup.draw(batch, alpha);
        super.draw(batch, alpha);
    }

    @Override
    protected void drawChildren(Batch batch, float parentAlpha) {
        itemGroup.drawChildrenBegin(batch, parentAlpha);
        super.drawChildren(batch, parentAlpha);
        itemGroup.drawChildrenEnd(batch, parentAlpha);
    }

    @Override
    public void positionChanged() {
        itemGroup.positionChanged();
    }

    @Override
    public void sizeChanged() {
        itemGroup.sizeChanged();
    }
}
