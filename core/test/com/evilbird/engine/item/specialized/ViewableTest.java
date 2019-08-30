/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.specialized;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.UnitType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static com.evilbird.test.data.item.TestSkin.newTestSkin;

/**
 * Instances of this unit test validate the {@link Viewable} class.
 *
 * @author Blair Butterworth
 */
public class ViewableTest extends GameTestCase
{
    private Viewable viewable;

    @Before
    public void setup() {
        super.setup();
        viewable = newAnimatedItem();
        Mockito.when(itemFactory.get(Mockito.any())).thenReturn(viewable);
    }

    private Viewable newAnimatedItem() {
        Viewable item = new Viewable(newTestSkin());
        item.setType(UnitType.Footman);
        item.setSize(56, 78);
        item.setPosition(12, 34);
        item.setDirection(new Vector2(1, 1));
        item.setAnimation(UnitAnimation.Build);
        item.setSound(UnitSound.Attack);
        return item;
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(Viewable.class)
            .withDeserializedForm(viewable)
            .withSerializedResource("/item/animateditem.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(Viewable.class)
            .withMockedTransientFields()
            .excludeTransientFields()
            .verify();
    }
}