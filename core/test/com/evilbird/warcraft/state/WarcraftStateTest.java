/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.test.data.behaviour.TestBehaviours;
import com.evilbird.test.data.item.TestItemRoots;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.behaviour.WarcraftBehaviour;
import com.evilbird.warcraft.item.hud.HudControl;
import com.evilbird.warcraft.item.hud.HudType;
import nl.jqno.equalsverifier.Warning;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Instances of this unit test validate the {@link WarcraftState} class.
 *
 * @author Blair Butterworth
 */
public class WarcraftStateTest extends GameTestCase
{
    private WarcraftState state;
    private ItemRoot world;
    private ItemRoot hud;
    private Item hudControl;
    private Behaviour behaviour;

    @Before
    public void setup() {
        super.setup();

        world = TestItemRoots.newTestRoot(new TextIdentifier("world"));
        hudControl = TestItems.newItem(new TextIdentifier("resources"), HudControl.ResourcePane);
        hud = TestItemRoots.newTestRoot(HudType.Human, hudControl);
        behaviour = TestBehaviours.newBehaviour(WarcraftBehaviour.Human1);
        state = new WarcraftState(world, hud, behaviour);

        respondWithItem(HudType.Human, () -> hudControl);
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
            .withMockedType(ItemRoot.class)
            .withMockedTransientFields()
            .excludeTransientFields()
            .suppress(Warning.REFERENCE_EQUALITY)
            .verify();
    }
}