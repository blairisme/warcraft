/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.path;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.evilbird.engine.item.spatial.ItemNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ItemNodePath implements GraphPath<ItemNode>
{
    public final List<ItemNode> nodes;

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
    public void add(ItemNode node) {
        nodes.add(node);
    }

    @Override
    public ItemNode get(int index) {
        return nodes.get(index);
    }

    @Override
    public void reverse() {
        Collections.reverse(nodes);
    }

    @Override
    public Iterator<ItemNode> iterator() {
        return nodes.iterator();
    }

    public ListIterator<ItemNode> listIterator() {
        return nodes.listIterator();
    }
}
