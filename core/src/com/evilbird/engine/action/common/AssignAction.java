/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;

import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.not;
import static com.evilbird.engine.item.utility.ItemPredicates.hasAction;

/**
 * Instances of this {@link Action} assign an action to a specified item.
 *
 * @author Blair Butterworth
 */
public class AssignAction extends BasicAction
{
    private Action action;
    private ActionRecipient recipient;
    private Predicate<Item> condition;

    public AssignAction(Action action, ActionRecipient recipient, Predicate<Item> condition) {
        this.action = action;
        this.recipient = recipient;
        this.condition = condition;
    }

    public static Action assign(Action action, ActionRecipient recipient, Predicate<Item> condition) {
        return new AssignAction(action, recipient, condition);
    }

    public static Action assignIfAbsent(Action action, ActionRecipient recipient) {
        return assign(action, recipient, not(hasAction(action.getIdentifier())));
    }

    @Override
    public boolean act(float delta) {
        Item item = ActionUtils.getRecipient(this, recipient);
        if (condition.test(item)) {
            action.reset();
            action.setItem(item);
            item.addAction(action);
        }
        return true;
    }
}
