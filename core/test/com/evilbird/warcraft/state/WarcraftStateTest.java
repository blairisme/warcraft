/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.badlogic.gdx.audio.Music;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.test.data.behaviour.TestBehaviours;
import com.evilbird.test.data.item.TestItemRoots;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.behaviour.WarcraftBehaviour;
import com.evilbird.warcraft.object.display.HudControl;
import com.evilbird.warcraft.object.display.HudType;
import nl.jqno.equalsverifier.Warning;
import org.junit.Before;
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

        world = TestItemRoots.newTestRoot(new TextIdentifier("world"));
        hudControl = TestItems.newItem(new TextIdentifier("resources"), HudControl.ResourcePane);
        hud = TestItemRoots.newTestRoot(HudType.Default, hudControl);
        behaviour = TestBehaviours.newBehaviour(WarcraftBehaviour.Human1);
        context = new WarcraftContext(Human, Summer);
        music = Mockito.mock(Music.class);
        state = new WarcraftState(world, hud, behaviour, music, context);

        respondWithItem(HudType.Default, () -> hudControl);
        respondWithBehaviour(behaviour, WarcraftBehaviour.Human1);
    }

    @Test
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