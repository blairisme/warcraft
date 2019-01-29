/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.function.Supplier;
import com.evilbird.engine.item.Item;

import static com.evilbird.engine.common.function.Suppliers.constantValue;

/**
 * Instances of this {@link Action} assign an <code>Action</code> to a given
 * {@link Item}.
 *
 * @author Blair Butterworth
 */
public class AssignAction extends BasicAction
{
    private Action action;
    private Supplier<Item> itemSupplier;

    public AssignAction(Action action, Item item) {
        this(action, constantValue(item));
    }

    public AssignAction(Action action, Supplier<Item> itemSupplier) {
        this.action = action;
        this.itemSupplier = itemSupplier;
    }

    @Override
    public boolean act(float delta) {
        Item item = itemSupplier.get();
        item.addAction(action);
        return true;
    }
}
