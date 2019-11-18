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

import java.util.Collection;

/**
 * Instances of this {@link Action} copy the subject and target of a given
 * delegate when invoked.
 *
 * @author Blair Butterworth
 */
public class CopyAction extends DelegateAction
{
    private Collection<Action> receivers;

    public CopyAction(Action delegate, Collection<Action> receivers) {
        super(delegate);
        this.receivers = receivers;
    }

    @Override
    public boolean act(float delta) {
        boolean result = delegate.act(delta);
        receivers.forEach(receiver -> receiver.setSubject(delegate.getSubject()));
        receivers.forEach(receiver -> receiver.setTarget(delegate.getTarget()));
        return result;
    }
}
