/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.fog;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.test.data.item.TestItemRoots;
import com.evilbird.test.data.item.TestPlayers;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.layer.LayerIdentifier;
import com.evilbird.warcraft.object.layer.LayerType;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.evilbird.test.data.item.TestSkins.newLayerSkin;

/**
 * Instances of this unit test validate the {@link Fog} class.
 *
 * @author Blair Butterworth
 */
public class FogTest extends GameTestCase
{
    private Fog fog;
    private GameObjectContainer root;
    private Player player;
    private EventQueue events;
    private TiledMapTileLayer layer;

    @Before
    public void setup() {
        super.setup();

        events = new EventQueue();
        layer = new TiledMapTileLayer(2, 2, 32, 32);

        root = TestItemRoots.newTestRoot("World");
        player = TestPlayers.newTestPlayer("Player1", root);

        fog = new Fog(newLayerSkin(), events);
        fog.setLayer(layer);
        fog.setIdentifier(new LayerIdentifier("data/levels/human/level1.tmx", "OpaqueFog", layer));
        fog.setType(LayerType.OpaqueFog);

        root.removeObjects();
        player.removeObjects();

        root.addObject(player);
        player.setRoot(root);
        player.addObject(fog);

        fog.update(1);
        respondWithItem(fog);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(GameObject.class)
            .withDeserializedForm(fog)
            .withSerializedResource("/warcraft/item/fog.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(Fog.class)
            .withMockedTransientFields()
            .excludeTransientFields()
            .verify();
    }
}