/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.common.function.Predicate;

public class RequirementAction<T extends Action> extends DelegateAction
{
    private Predicate<? super T> requirement;

    public RequirementAction(T delegate, Predicate<? super T> requirement) {
        super(delegate);
        this.requirement = requirement;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean act(float delta) {
        if (! requirement.test((T)delegate)) {
            cancel();
        }
        return delegate.act(delta);
    }
}
