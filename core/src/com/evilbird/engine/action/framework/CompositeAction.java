/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionException;
import com.evilbird.engine.common.serialization.SerializedInitializer;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemReference;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Instances of this {@link Action Action} provide common implementation
 * for those actions which represent collection of child actions.
 *
 * @author Blair Butterworth
 */
public abstract class CompositeAction extends BasicAction
{
    protected transient List<Action> actions;

    public CompositeAction() {
        actions = new ArrayList<>();
    }

    public CompositeAction(List<Action> actions) {
        this.actions = new ArrayList<>(actions);
    }

    public CompositeAction(Action... actions) {
        this.actions = new ArrayList<>(actions.length);
        Collections.addAll(this.actions, actions);
    }

    public boolean isEmpty() {
        return actions.isEmpty();
    }

    public Collection<Action> getActions() {
        return Collections.unmodifiableList(actions);
    }

    protected Action add(Action action) {
        actions.add(action);
        return action;
    }

    protected Action get(int index) {
        return actions.get(index);
    }

    @Override
    public ActionException getError() {
        for (Action delegate: actions) {
            if (delegate.hasError()) {
                return delegate.getError();
            }
        }
        return null;
    }

    @Override
    public void restart() {
        super.restart();
        for (Action delegate: actions) {
            delegate.restart();
        }
    }

    @Override
    public void reset() {
        super.reset();
        for (Action delegate: actions) {
            delegate.reset();
        }
    }

    @Override
    public void setCause(UserInput cause) {
        super.setCause(cause);
        for (Action delegate: actions) {
            delegate.setCause(cause);
        }
    }

    @Override
    public void setError(ActionException error) {
        for (Action delegate: actions) {
            delegate.setError(error);
        }
    }

    @Override
    public void setItem(Item item) {
        super.setItem(item);
        for (Action delegate: actions) {
            delegate.setItem(item);
        }
    }

    @Override
    public void setItemReference(ItemReference reference) {
        super.setItemReference(reference);
        for (Action action: actions) {
            if (action instanceof BasicAction) {
                BasicAction basic = (BasicAction)action;
                basic.setItemReference(reference);
            }
        }
    }

    @Override
    public void setTarget(Item target) {
        super.setTarget(target);
        for (Action delegate: actions) {
            delegate.setTarget(target);
        }
    }

    @Override
    public void setTargetReference(ItemReference reference) {
        super.setTargetReference(reference);
        for (Action action: actions) {
            if (action instanceof BasicAction) {
                BasicAction basic = (BasicAction)action;
                basic.setTargetReference(reference);
            }
        }
    }

    @Override
    public void setRoot(ItemComposite root) {
        super.setRoot(root);
        for (Action action: actions) {
            if (action instanceof BasicAction) {
                BasicAction basic = (BasicAction)action;
                basic.setRoot(root);
            }
        }
    }

    @SerializedInitializer
    protected void initialize() {
        for (Action action: actions) {
            if (action instanceof BasicAction) {
                BasicAction basic = (BasicAction)action;
                basic.setItemReference(this.getItemReference());
                basic.setTargetReference(this.getTargetReference());
            }
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper("base")
            .append("actions", actions)
            .toString();
    }
}
