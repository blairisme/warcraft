/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.data.item;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;

import static com.evilbird.test.data.item.TestPlayers.newTestPlayer;

public class TestItemRoots
{
    private TestItemRoots() {
    }

    public static ItemRoot newTestRoot(String id) {
        return newTestRoot(new TextIdentifier(id));
    }

    public static ItemRoot newTestRoot(Identifier id){
        ItemRoot root = new ItemRoot();
        root.setIdentifier(id);
        root.setSpatialGraph(new ItemGraph(32, 32, 128, 128));
        root.addItem(newTestPlayer(new TextIdentifier("player1"), root));
        root.addItem(newTestPlayer(new TextIdentifier("player2"), root));
        return root;
    }

    public static ItemRoot newTestRoot(Identifier id, Item ... items){
        ItemRoot root = new ItemRoot();
        root.setIdentifier(id);
        for (Item item: items) {
            root.addItem(item);
        }
        return root;
    }
}
