/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class ItemAction
{
    Action delegate;
    private Item item;
    private Item target;

    public ItemAction() {
        this.delegate = new ActionImpl();
    }

    public abstract boolean act(float delta);

    public Item getItem() {
        return item;
    }

    public Item getTarget() {
        return target;
    }

    public void reset() {
        delegate.reset();
    }

    public void restart() {
        delegate.restart();
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setTarget(Item target) {
        this.target = target;
    }

    private class ActionImpl extends Action {
        @Override
        public Actor getActor() {
            return ItemAction.this.item.delegate;
        }

        @Override
        public Actor getTarget() {
            return ItemAction.this.target.delegate;
        }

        @Override
        public void setActor(Actor actor) {
            Item item = actor != null ? (Item)actor.getUserObject() : null;
            ItemAction.this.setItem(item);
        }

        @Override
        public void setTarget(Actor target) {
            Item item = target != null ? (Item)target.getUserObject() : null;
            ItemAction.this.setTarget(item);
        }

        @Override
        public boolean act(float delta) {
            return ItemAction.this.act(delta);
        }
    }
}
