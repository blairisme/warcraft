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
import com.evilbird.engine.object.GameObject;

import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.not;
import static com.evilbird.engine.object.utility.GameObjectPredicates.hasAction;

/**
 * Instances of this {@link Action} assign an action to a specified item.
 *
 * @author Blair Butterworth
 */
public class AssignAction extends BasicAction
{
    private Action action;
    private ActionRecipient recipient;
    private Predicate<GameObject> condition;

    public AssignAction(Action action, ActionRecipient recipient, Predicate<GameObject> condition) {
        this.action = action;
        this.recipient = recipient;
        this.condition = condition;
    }

    public static Action assign(Action action, ActionRecipient recipient, Predicate<GameObject> condition) {
        return new AssignAction(action, recipient, condition);
    }

    public static Action assignIfAbsent(Action action, ActionRecipient recipient) {
        return assign(action, recipient, not(hasAction(action.getIdentifier())));
    }

    @Override
    public boolean act(float delta) {
        GameObject gameObject = ActionUtils.getRecipient(this, recipient);
        if (condition.test(gameObject)) {
            action.reset();
            action.setItem(gameObject);
            gameObject.addAction(action);
        }
        return true;
    }
}
