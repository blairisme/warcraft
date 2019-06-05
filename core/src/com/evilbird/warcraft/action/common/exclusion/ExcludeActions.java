/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.exclusion;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.ActionRecipient;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.events.Events;

import static com.evilbird.engine.action.common.ActionRecipient.Subject;
import static com.evilbird.engine.action.common.AnimateAction.animate;
import static com.evilbird.engine.action.common.DisableAction.disable;
import static com.evilbird.engine.action.common.DisableAction.enable;
import static com.evilbird.engine.action.common.VisibleAction.hide;
import static com.evilbird.engine.action.common.VisibleAction.show;
import static com.evilbird.warcraft.action.select.SelectAction.deselect;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;

/**
 * Instances of this class provide common action sequences related to excluding
 * and restoring items.
 *
 * @author Blair Butterworth
 */
public class ExcludeActions
{
    private ExcludeActions() {
    }

    public static Action exclude(Events events) {
        return exclude(Subject, events);
    }

    public static Action exclude(ActionRecipient recipient, Events events) {
        return new ParallelAction(hide(recipient), disable(recipient), deselect(recipient, events));
    }

    public static Action restore() {
        return restore(Subject);
    }

    public static Action restore(ActionRecipient recipient) {
        return new ParallelAction(show(recipient), enable(recipient), animate(recipient, Idle));
    }
}
