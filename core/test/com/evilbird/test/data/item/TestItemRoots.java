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
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;

import static com.evilbird.test.data.item.TestPlayers.newTestPlayer;

public class TestItemRoots
{
    private TestItemRoots() {
    }

    public static GameObjectContainer newTestRoot(String id) {
        return newTestRoot(new TextIdentifier(id));
    }

    public static GameObjectContainer newTestRoot(Identifier id){
        GameObjectContainer root = new GameObjectContainer();
        root.setIdentifier(id);
        root.setSpatialGraph(new GameObjectGraph(32, 32, 128, 128));
        root.addObject(newTestPlayer(new TextIdentifier("player1"), root));
        root.addObject(newTestPlayer(new TextIdentifier("player2"), root));
        return root;
    }

    public static GameObjectContainer newTestRoot(Identifier id, GameObject... gameObjects){
        GameObjectContainer root = new GameObjectContainer();
        root.setIdentifier(id);
        for (GameObject gameObject : gameObjects) {
            root.addObject(gameObject);
        }
        return root;
    }
}
