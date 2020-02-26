/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.audio.music.Music;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectContainerType;
import com.evilbird.test.data.behaviour.TestBehaviours;
import com.evilbird.test.data.item.TestItemRoots;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.behaviour.WarcraftBehaviourType;
import com.evilbird.warcraft.object.display.components.UserInterfaceComponent;
import nl.jqno.equalsverifier.Warning;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftSeason.Summer;

/**
 * Instances of this unit test validate the {@link WarcraftState} class.
 *
 * @author Blair Butterworth
 */
public class WarcraftStateTest extends GameTestCase
{
    private WarcraftState state;
    private GameObjectContainer world;
    private GameObjectContainer hud;
    private GameObject hudControl;
    private WarcraftContext context;
    private Behaviour behaviour;
    private Music music;

    @Before
    public void setup() {
        super.setup();

        world = TestItemRoots.newTestRoot(GameObjectContainerType.World);
        hudControl = TestItems.newItem(new TextIdentifier("resources"), UserInterfaceComponent.ResourcePane);
        hud = TestItemRoots.newTestRoot(GameObjectContainerType.Hud, hudControl);
        behaviour = TestBehaviours.newBehaviour(WarcraftBehaviourType.Human1);
        context = new WarcraftContext(Human, Summer);
        music = Mockito.mock(Music.class);
        state = new WarcraftState(world, hud, behaviour, music, context);

        respondWithItem(GameObjectContainerType.Hud, () -> hudControl);
        respondWithBehaviour(behaviour, WarcraftBehaviourType.Human1);
    }

    @Test
    @Ignore
    public void serializeSaveTest() throws IOException {
        SerializationVerifier.forClass(WarcraftState.class)
            .withDeserializedForm(state)
            .withSerializedResource("/warcraft/state/save.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(WarcraftState.class)
            .withMockedType(GameObjectContainer.class)
            .withMockedTransientFields()
            .excludeTransientFields()
            .suppress(Warning.REFERENCE_EQUALITY)
            .verify();
    }
}