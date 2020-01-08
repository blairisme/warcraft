/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object.spatial;

import com.badlogic.gdx.ai.pfa.Connection;

import java.util.function.Predicate;

/**
 * A wrapper for an {@link GameObjectNode} {@link Predicate} that allows it to
 * be used as a {@link Connection} {@code Predicate}.
 *
 * @author Blair Butterworth
 */
public class ConnectionPredicate implements Predicate<Connection<GameObjectNode>>
{
    private Predicate<GameObjectNode> delegate;

    public ConnectionPredicate(Predicate<GameObjectNode> delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean test(Connection<GameObjectNode> connection) {
        return delegate.test(connection.getToNode());
    }
}