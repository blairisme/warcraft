/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.terrain;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.evilbird.engine.item.Item;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
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
        SerializationVerifier.forClass(Item.class)
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