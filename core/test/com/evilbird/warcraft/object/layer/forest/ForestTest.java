/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.forest;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.object.layer.LayerGroupStyle;
import com.evilbird.warcraft.object.layer.LayerIdentifier;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

/**
 * Instances of this unit test validate the {@link Forest} class.
 *
 * @author Blair Butterworth
 */
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

        Skin skin = new Skin();
        skin.add("default", Mockito.mock(LayerGroupStyle.class), LayerGroupStyle.class);

        forest = new Forest(skin);
        forest.setLayer(layer);
        forest.setIdentifier(identifier);
        forest.setType(identifier.getType());
        forest.update(1);

        respondWithItem(forest);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(GameObject.class)
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