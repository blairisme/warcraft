/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant;

import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.object.unit.UnitType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

/**
 * Instances of this unit test validate logic in the {@link Combatant} class.
 *
 * @author Blair Butterworth
 */
public class CombatantTest extends GameTestCase
{
    private Combatant combatant;

    @Before
    public void setup() {
        super.setup();
        combatant = TestCombatants.newTestCombatant(new TextIdentifier("footman"), UnitType.Footman);
        Mockito.when(objectFactory.get(UnitType.Footman)).thenReturn(combatant);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(GameObject.class)
            .withDeserializedForm(combatant)
            .withSerializedResource("/warcraft/item/combatant.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(Combatant.class)
            .withMockedTransientFields()
            .excludeTransientFields()
            .verify();
    }
}
