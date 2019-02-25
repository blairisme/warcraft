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
import com.evilbird.engine.common.function.Predicate;

/**
 * Instances of this {@link Action} execute a delegate action only if a given
 * {@link Predicate requirement} is satisfied.
 *
 * @author Blair Butterworth
 */
public class RequirementAction extends DelegateAction
{
    private Predicate<Action> requirement;

    public RequirementAction(Action delegate, Predicate<Action> requirement) {
        super(delegate);
        this.requirement = requirement;
    }

    @Override
    public boolean act(float delta) {
        if (! requirement.test(delegate)) {
            cancel();
            return true;
        }
        return delegate.act(delta);
    }
}
