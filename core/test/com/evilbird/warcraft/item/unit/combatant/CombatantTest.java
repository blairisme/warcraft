/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

import com.evilbird.engine.item.Item;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.human.FootmanFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static com.evilbird.test.data.device.TestAssets.newAssetManagerMock;

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
        FootmanFactory footmanFactory = new FootmanFactory(newAssetManagerMock());
        combatant = (Combatant)footmanFactory.get();
        Mockito.when(itemFactory.newItem(UnitType.Footman)).thenReturn(combatant);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(Item.class)
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
