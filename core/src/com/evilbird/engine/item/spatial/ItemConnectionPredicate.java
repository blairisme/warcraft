/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.spatial;

import com.badlogic.gdx.ai.pfa.Connection;

import java.util.function.Predicate;

/**
 * A wrapper for an {@link ItemNode} {@link Predicate} that allows it to be
 * used as a {@link Connection} <code>Predicate</code>.
 *
 * @author Blair Butterworth
 */
public class ItemConnectionPredicate implements Predicate<Connection<ItemNode>>
{
    private Predicate<ItemNode> delegate;

    public ItemConnectionPredicate(Predicate<ItemNode> delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean test(Connection<ItemNode> connection) {
        return delegate.test(connection.getToNode());
    }
}