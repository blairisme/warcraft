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
import com.evilbird.engine.common.function.Supplier;

/**
 * @author Blair Butterworth
 */
public class RequirementAction extends DelegateAction
{
    private Supplier<Boolean> requirement;

    public RequirementAction(Action delegate, Supplier<Boolean> requirement) {
        super(delegate);
    }

    @Override
    public boolean act(float delta) {
        if (! requirement.get()) {
            cancel();
        }
        return delegate.act(delta);
    }
}
