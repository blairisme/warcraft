/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.forest;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.evilbird.engine.item.Item;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class ForestTest extends GameTestCase
{
    private Forest forest;

    @Before
    public void setup() {
        super.setup();

        TiledMapTileLayer layer = new TiledMapTileLayer(2, 2, 32, 32);
        layer.setCell(0, 0, Mockito.mock(Cell.class));
        layer.setCell(0, 1, Mockito.mock(Cell.class));
        layer.setCell(1, 0, Mockito.mock(Cell.class));
        layer.setCell(1, 1, Mockito.mock(Cell.class));

        LayerIdentifier identifier = new LayerIdentifier("data/levels/human/level1.tmx", "Map", layer);

        forest = new Forest();
        forest.setLayer(layer);
        forest.setIdentifier(identifier);
        forest.setType(identifier.getType());
        forest.update(1);

        respondWithItem(forest);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(Item.class)
            .withDeserializedForm(forest)
            .withSerializedResource("/warcraft/item/forest.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(Forest.class)
            .withMockedTransientFields()
            .excludeTransientFields()
            .verify();
    }
}