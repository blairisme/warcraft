/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.production;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemType;

public class CreateAction extends BasicAction
{
    private ItemType itemType;
    private ItemFactory itemFactory;
    private CreateObserver observer;

    public CreateAction(ItemType type, CreateObserver observer) {
        this.itemType = type;
        this.itemFactory = GameService.getInstance().getItemFactory();
        this.observer = observer;
    }

    public CreateAction(ItemFactory factory, ItemType type, CreateObserver observer) {
        this.itemType = type;
        this.itemFactory = factory;
        this.observer = observer;
    }

    public static CreateAction create(Producible producible, CreateObserver observer) {
        return new CreateAction(producible.getItemType(), observer);
    }

    @Override
    public boolean act(float delta) {
        Item item = itemFactory.newItem(itemType);
        setPosition(item);
        setParent(item);
        return true;
    }

    private void setPosition(Item item) {
        Item producer = getItem();
        Vector2 position = getPosition(item, producer);
        item.setPosition(position);
    }

    private Vector2 getPosition(Item item, Item producer) {
        Vector2 itemSize = item.getSize();
        Vector2 referencePosition = producer.getPosition();
        return new Vector2(referencePosition.x - itemSize.x, referencePosition.y);
    }

    private void setParent(Item item) {
        Item producer = getItem();
        ItemComposite parent = getParent(producer);
        parent.addItem(item);
    }

    private ItemComposite getParent(Item producer) {
        if (producer instanceof ItemComposite) {
            return (ItemComposite)producer;
        }
        return producer.getParent();
    }
}




