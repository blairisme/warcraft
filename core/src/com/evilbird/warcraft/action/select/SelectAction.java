/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.ActionRecipient;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Selectable;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;

import static com.evilbird.engine.action.common.ActionUtils.getRecipient;

/**
 * An {@link Action} that sets an {@link Item Items} selected state.
 *
 * @author Blair Butterworth
 */
public class SelectAction extends BasicAction
{
    private Events events;
    private boolean selected;
    private ActionRecipient recipient;

    public SelectAction(ActionRecipient recipient, boolean selected, Events events) {
        this.recipient = recipient;
        this.selected = selected;
        this.events = events;
    }

    public static SelectAction select(Events events) {
        return new SelectAction(ActionRecipient.Subject, true, events);
    }

    public static SelectAction select(ActionRecipient source, Events events) {
        return new SelectAction(source, true, events);
    }

    public static SelectAction deselect(Events events) {
        return new SelectAction(ActionRecipient.Subject, false, events);
    }

    public static SelectAction deselect(ActionRecipient source, Events events) {
        return new SelectAction(source, false, events);
    }

    @Override
    public boolean act(float time) {
        Selectable selectable = (Selectable)getRecipient(this, recipient);
        if (selectable.getSelected() != selected) {
            selectable.setSelected(selected);

            if (events != null) {
                events.add(new SelectEvent(selectable, selected));
            }
        }
        return true;
    }
}
