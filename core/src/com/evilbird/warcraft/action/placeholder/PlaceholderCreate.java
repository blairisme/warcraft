/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.engine.action.common.CreateAction;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemOperations;

import javax.inject.Inject;

/**
 * Instances of this class provide {@link Action Actions} that add a building
 * placeholder.
 *
 * @author Blair Butterworth
 */
public class PlaceholderCreate extends CreateAction
{
    @Inject
    public PlaceholderCreate(ItemFactory itemFactory) {
        super(itemFactory);
    }

    @Override
    public void setItem(Item item) {
        super.setItem(item);
        if (item != null) {
            setPosition(ItemOperations.getScreenCenter(item.getRoot()));
        }
    }
}
