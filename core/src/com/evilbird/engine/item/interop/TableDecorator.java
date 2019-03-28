/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.interop;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.evilbird.engine.item.Item;

/**
 * Instances of this class decorate the LibGDX {@link Table} class with an
 * {@link Item}, allowing the Item to receive events from the Table without
 * inheriting from it.
 *
 * @author Blair Butterworth
 */
public class TableDecorator extends Table
{
    private Item item;

    public TableDecorator(Item item) {
        this.item = item;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        item.update(delta);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        item.draw(batch, alpha);
        super.draw(batch, alpha);

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
