/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.common.path;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.evilbird.engine.object.spatial.GameObjectNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ItemNodePath implements GraphPath<GameObjectNode>
{
    public final List<GameObjectNode> nodes;

    public ItemNodePath() {
        nodes = new ArrayList<>();
    }

    @Override
    public void clear() {
        nodes.clear();
    }

    @Override
    public int getCount() {
        return nodes.size();
    }

    public boolean isEmpty() {
        return nodes.isEmpty();
    }

    @Override
    public void add(GameObjectNode node) {
        nodes.add(node);
    }

    @Override
    public GameObjectNode get(int index) {
        return nodes.get(index);
    }

    @Override
    public void reverse() {
        Collections.reverse(nodes);
    }

    @Override
    public Iterator<GameObjectNode> iterator() {
        return nodes.iterator();
    }

    public ListIterator<GameObjectNode> listIterator() {
        return nodes.listIterator();
    }
}
