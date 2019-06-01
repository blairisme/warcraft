/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.fog;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
 * Instances of this unit test validate the {@link Fog} class.
 *
 * @author Blair Butterworth
 */
public class FogTest extends GameTestCase
{
    private Fog fog;

    @Before
    public void setup() {
        super.setup();

        TiledMapTileLayer layer = new TiledMapTileLayer(2, 2, 32, 32);
        layer.setCell(0, 0, Mockito.mock(Cell.class));
        layer.setCell(0, 1, Mockito.mock(Cell.class));
        layer.setCell(1, 0, Mockito.mock(Cell.class));
        layer.setCell(1, 1, Mockito.mock(Cell.class));

        LayerIdentifier identifier = new LayerIdentifier("data/levels/human/level1.tmx", "OpaqueFog", layer);

        Skin skin = new Skin();
        skin.add("default", Mockito.mock(FogStyle.class), FogStyle.class);

        fog = new Fog(skin);
        fog.setLayer(layer);
        fog.setIdentifier(identifier);
        fog.setType(identifier.getType());
        //fog.apply(1);

        respondWithItem(fog);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(Item.class)
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