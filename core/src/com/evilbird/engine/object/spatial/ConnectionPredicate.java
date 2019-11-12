/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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