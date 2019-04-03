/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.item.Item;

import java.util.function.Consumer;

/**
 * @author Blair Butterworth
 */
public class LambdaAction extends BasicAction
{
    private Consumer<Item> lambda;

    public LambdaAction(Consumer<Item> lambda) {
        this.lambda = lambda;
    }

    @Override
    public boolean act(float delta) {
        lambda.accept(getItem());
        return true;
    }
}
