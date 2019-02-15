/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.test;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.DelegateAction;

public class MockDelegateAction extends DelegateAction {
    public MockDelegateAction(Action delegate) {
        super(delegate);
    }

    @Override
    public boolean act(float delta) {
        return false;
    }
}