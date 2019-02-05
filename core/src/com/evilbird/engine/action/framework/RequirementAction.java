/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.common.function.Predicate;

public class RequirementAction extends DelegateAction
{
    private Predicate<Action> requirement;

    public RequirementAction(Action delegate, Predicate<Action> requirement) {
        super(delegate);
        this.requirement = requirement;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean act(float delta) {
        if (! requirement.test((Action)delegate)) {
            cancel();
            return true;
        }
        return delegate.act(delta);
    }
}
