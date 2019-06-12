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
import com.evilbird.engine.item.Item;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * An {@link Action} implementation that wraps a given {@link BiConsumer
 * lambda}, executing it once invoked.
 *
 * @author Blair Butterworth
 */
public class LambdaAction extends BasicAction
{
    private BiConsumer<Item, Item> lambda;

    /**
     * Constructs a new instance of this class given the lambda that will be
     * called when the action is invoked.
     *
     * @param lambda    a {@link BiConsumer} that will receive the
     *                  current item and whileTarget when invoked. This parameter
     *                  cannot be {@code null}.
     */
    public LambdaAction(BiConsumer<Item, Item> lambda) {
        Objects.requireNonNull(lambda);
        this.lambda = lambda;
    }

    @Override
    public boolean act(float delta) {
        lambda.accept(getItem(), getTarget());
        return true;
    }
}
