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
    private ItemType itemType;
    private ItemFactory itemFactory;
    private CreateObserver observer;
    private Consumer<Item> properties;

    public CreateAction(ItemType type, Consumer<Item> properties, CreateObserver observer) {
        this.itemType = type;
        this.observer = observer;
        this.properties = properties;
        this.itemFactory = GameService.getInstance().getItemFactory();
    }

    public CreateAction(ItemFactory factory, ItemType type, Consumer<Item> properties, CreateObserver observer) {
        this.itemType = type;
        this.observer = observer;
        this.properties = properties;
        this.itemFactory = factory;
    }

    public static CreateAction create(ItemType type, Consumer<Item> properties, CreateObserver observer) {
        return new CreateAction(type, properties, observer);
    }

    @Override
    public boolean act(float delta) {
        Item item = createItem();
        setTarget(item);
        setParent(item);
        setProperties(item);
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
        if (observer != null) {
            observer.onCreate(item);
        }
    }
}




