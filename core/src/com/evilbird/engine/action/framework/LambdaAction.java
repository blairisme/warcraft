/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.object.GameObject;

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
    private BiConsumer<GameObject, GameObject> lambda;

    /**
     * Constructs a new instance of this class given the lambda that will be
     * called when the action is invoked.
     *
     * @param lambda    a {@link BiConsumer} that will receive the
     *                  current item and target when invoked. This parameter
     *                  cannot be {@code null}.
     */
    public LambdaAction(BiConsumer<GameObject, GameObject> lambda) {
        Objects.requireNonNull(lambda);
        this.lambda = lambda;
    }

    @Override
    public boolean act(float delta) {
        lambda.accept(getSubject(), getTarget());
        return true;
    }
}
