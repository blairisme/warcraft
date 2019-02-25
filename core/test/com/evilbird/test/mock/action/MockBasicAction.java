/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.mock.action;

import com.evilbird.engine.action.framework.BasicAction;

public class MockBasicAction extends BasicAction
{
    @Override
    public boolean act(float delta) {
        return true;
    }
}