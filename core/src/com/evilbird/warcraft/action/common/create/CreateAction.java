/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.create;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemType;

import java.util.function.Consumer;

/**
 * Instances of this {@link Action} create a new {@link Item} when invoked.
 *
 * @author Blair Butterworth
 */
public class CreateAction extends BasicAction
{
    private Events events;
    private ItemType itemType;
    private ItemFactory itemFactory;
    private Consumer<Item> properties;

    public CreateAction(ItemType type, Consumer<Item> properties, Events events) {
        this.itemType = type;
        this.events = events;
        this.properties = properties;
        this.itemFactory = GameService.getInstance().getItemFactory();
    }

    public static CreateAction create(ItemType type, Consumer<Item> properties, Events events) {
        return new CreateAction(type, properties, events);
    }

    public static CreateAction create(ItemType type, Consumer<Item> properties) {
        return new CreateAction(type, properties, events -> {});
    }

    public static CreateAction create(ItemType type, Events events) {
        return new CreateAction(type, item -> {}, events);
    }

    @Override
    public boolean act(float delta) {
        Item item = createItem();
        setProperties(item);
        setTarget(item);
        setParent(item);
        notifyObserver(item);
        return true;
    }

    protected Item createItem() {
        return itemFactory.newItem(itemType);
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

    protected void setProperties(Item item) {
        if (properties != null) {
            properties.accept(item);
        }
    }

    protected void notifyObserver(Item item) {
        if (events != null) {
            events.add(new CreateEvent(item));
        }
    }
}




