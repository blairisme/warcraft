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

/**
 * Instances of this {@link Action} copy the subject and target of a given
 * delegate when invoked.
 *
 * @author Blair Butterworth
 */
public class CopyAction extends DelegateAction
{
    private Action host;

    public CopyAction(Action delegate, Action host) {
        super(delegate);
        this.host = host;
    }

    @Override
    public boolean act(float delta) {
        boolean result = delegate.act(delta);
        host.setItem(delegate.getItem());
        host.setTarget(delegate.getTarget());
        return result;
    }
}
