/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.confirm;

import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmTarget;

/**
 * Instances of this class show a confirmation effect over a given {@link Item}.
 *
 * @author Blair Butterworth
 */
public class ConfirmItem extends ConfirmAction
{
    @Inject
    public ConfirmItem(ItemFactory itemFactory) {
        super(itemFactory);
        setIdentifier(ConfirmTarget);
    }

    @Override
    public void setTarget(Item target) {
        super.setTarget(target);
        setLocation(target.getPosition());
    }
}
