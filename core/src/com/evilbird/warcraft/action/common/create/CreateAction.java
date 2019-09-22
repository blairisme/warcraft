/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.create;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.function.Suppliers;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemType;

import javax.inject.Inject;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Instances of this {@link Action} create a new {@link Item} when invoked.
 *
 * @author Blair Butterworth
 */
public class CreateAction extends BasicAction
{
    protected Events events;
    protected ItemFactory factory;
    protected Supplier<ItemType> type;
    protected Consumer<Item> properties;
    protected Consumer<Item> recipient;

    @Inject
    public CreateAction(Events events, ItemFactory factory) {
        this.events = events;
        this.factory = factory;
    }

    public void setProperties(Consumer<Item> properties) {
        this.properties = properties;
    }

    public void setRecipient(Consumer<Item> recipient) {
        this.recipient = recipient;
    }

    public void setType(ItemType type) {
        this.type = Suppliers.constantValue(type);
    }

    public void setType(Supplier<ItemType> supplier) {
        this.type = supplier;
    }

    @Override
    public boolean act(float delta) {
        Item item = createItem();
        setProperties(item);
        setParent(item);
        notifyRecipient(item);
        notifyObserver(item);
        return true;
    }

    protected Item createItem() {
        return factory.get(type.get());
    }

    protected void setProperties(Item item) {
        if (properties != null) {
            properties.accept(item);
        }
    }

    protected void setParent(Item item) {
        Item producer = getItem();
        ItemComposite parent = getParent(producer);
        parent.addItem(item);
    }

    protected ItemComposite getParent(Item producer) {
        if (producer instanceof ItemComposite) {
            return (ItemComposite)producer;
        }
        return producer.getParent();
    }

    protected void notifyRecipient(Item item) {
        if (recipient != null) {
            recipient.accept(item);
        }
    }

    protected void notifyObserver(Item item) {
        if (events != null) {
            events.add(new CreateEvent(item));
        }
    }
}




