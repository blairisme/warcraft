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
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.test.data.item.TestItemRoots;
import com.evilbird.test.data.item.TestPlayers;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.layer.LayerGroupStyle;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import com.evilbird.warcraft.item.layer.LayerType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
    private ItemRoot root;
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

        root.clearItems();
        player.clearItems();

        root.addItem(player);
        player.setRoot(root);
        player.addItem(fog);

        fog.update(1);
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