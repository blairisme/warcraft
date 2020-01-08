/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.terrain;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.object.layer.LayerIdentifier;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

/**
 * Instances of this unit test validate the {@link Terrain} class.
 *
 * @author Blair Butterworth
 */
public class TerrainTest extends GameTestCase
{
    private Terrain terrain;

    @Before
    public void setup() {
        super.setup();

        TiledMapTileLayer layer = new TiledMapTileLayer(2, 2, 32, 32);
        layer.setCell(0, 0, Mockito.mock(TiledMapTileLayer.Cell.class));
        layer.setCell(0, 1, Mockito.mock(TiledMapTileLayer.Cell.class));
        layer.setCell(1, 0, Mockito.mock(TiledMapTileLayer.Cell.class));
        layer.setCell(1, 1, Mockito.mock(TiledMapTileLayer.Cell.class));

        LayerIdentifier identifier = new LayerIdentifier("data/levels/human/level1.tmx", "Map", layer);

        terrain = new Terrain();
        terrain.setLayer(layer);
        terrain.setIdentifier(identifier);
        terrain.setType(identifier.getType());

        respondWithItem(terrain);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(GameObject.class)
            .withDeserializedForm(terrain)
            .withSerializedResource("/warcraft/item/terrain.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(Terrain.class)
            .withMockedTransientFields()
            .excludeTransientFields()
            .verify();
    }
}