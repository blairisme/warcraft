/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object.spatial;

import com.evilbird.engine.common.collection.InterleavingIterator;
import com.evilbird.engine.object.GameObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Represents an iterable collection of {@link GameObjectNode GameObjectNodes}.
 *
 * @author Blair Butterworth
 */
public class GameObjectNodeSet implements Iterable<GameObject>
{
    private Collection<GameObjectNode> nodes;
    private Collection<Collection<GameObject>> occupants;

    public GameObjectNodeSet(Collection<GameObjectNode> nodes) {
        this.nodes = nodes;
        this.occupants = new ArrayList<>(nodes.size());
        for (GameObjectNode node: nodes) {
            this.occupants.add(node.occupants.values());
        }
    }

    public boolean contains(GameObjectNode node) {
        return nodes.contains(node);
    }

    @Override
    public Iterator<GameObject> iterator() {
        return new InterleavingIterator<>(occupants);
    }
}
