/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.animated;

import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.item.specialized.AnimatedItem;
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

public class AnimatedItemTest extends GameTestCase
{
    private AnimatedItem animatedItem;

    @Before
    public void setup() {
        super.setup();
        animatedItem = newAnimatedItem();
        Mockito.when(itemFactory.newItem(Mockito.any())).thenReturn(animatedItem);
    }

    private AnimatedItem newAnimatedItem() {
        AnimatedItem item = new AnimatedItem();
        item.setType(UnitType.Footman);
        item.setSize(56, 78);
        item.setPosition(12, 34);
        item.setDirection(1, 2, 3, 4);
        item.setAvailableAnimation(UnitAnimation.Build, Mockito.mock(DirectionalAnimation.class));
        item.setAnimation(UnitAnimation.Build);
        item.setAvailableSound(UnitSound.Attack, Mockito.mock(SoundEffect.class));
        item.setSound(UnitSound.Attack);
        return item;
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(AnimatedItem.class)
            .withDeserializedForm(animatedItem)
            .withSerializedResource("/item/animateditem.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(AnimatedItem.class)
            .withMockedTransientFields()
            .excludeTransientFields()
            .verify();
    }
}