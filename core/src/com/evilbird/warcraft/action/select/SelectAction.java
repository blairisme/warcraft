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
import com.evilbird.engine.item.Item;

import java.util.Objects;

import static com.evilbird.engine.action.common.ActionUtils.getRecipient;

/**
 * An {@link Action} that sets an {@link Item Items} selected state.
 *
 * @author Blair Butterworth
 */
public class SelectAction extends BasicAction
{
    private boolean selected;
    private ActionRecipient recipient;
    private SelectObserver observer;

    public SelectAction(ActionRecipient recipient, boolean selected, SelectObserver observer) {
        Objects.requireNonNull(observer);
        this.recipient = recipient;
        this.selected = selected;
        this.observer = observer;
    }

    public static SelectAction select(SelectObserver observer) {
        return new SelectAction(ActionRecipient.Subject, true, observer);
    }

    public static SelectAction select(ActionRecipient source, SelectObserver observer) {
        return new SelectAction(source, true, observer);
    }

    public static SelectAction deselect(SelectObserver observer) {
        return new SelectAction(ActionRecipient.Subject, false, observer);
    }

    public static SelectAction deselect(ActionRecipient source, SelectObserver observer) {
        return new SelectAction(source, false, observer);
    }

    @Override
    public boolean act(float time) {
        Selectable selectable = (Selectable)getRecipient(this, recipient);
        selectable.setSelected(selected);
        observer.onSelect((Item)selectable, selected);
        return true;
    }
}
