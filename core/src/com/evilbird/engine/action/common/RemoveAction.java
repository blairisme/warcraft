/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.function.Supplier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemPredicates;

public class RemoveAction extends BasicAction
{
//    private ItemComposite parent;
//    private Item item;
//    private Identifier id;
    private Supplier<Item> supplier;

    public RemoveAction(){
    }

    @Deprecated
    public RemoveAction(Item item) {
        setItem(item);
    }

    public RemoveAction(Identifier id) {
        supplier = () -> getItem().getParent().find(ItemPredicates.itemWithId(id));
    }

    @Override
    public boolean act(float delta) {


        Item item = supplier != null ? supplier.get() : getItem();
        ItemGroup parent = item.getParent();
        parent.removeItem(item);

//        if (item == null) {
//            item = parent.find(ItemPredicates.itemWithId(id));
//        }
//        if (item != null) {
//            item.clearActions();
//            parent.removeItem(item);
//        }
        return true;
    }

}
