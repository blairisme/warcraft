/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.object.control;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.object.AnimatedObject;
import com.evilbird.test.data.item.TestItemRoots;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import com.evilbird.warcraft.object.unit.UnitSound;
import com.evilbird.warcraft.object.unit.UnitType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static com.evilbird.test.data.item.TestSkins.newTestSkin;

/**
 * Instances of this unit test validate the {@link AnimatedObject} class.
 *
 * @author Blair Butterworth
 */
public class AnimatedObjectTest extends GameTestCase
{
    private AnimatedObject animatedObject;

    @Before
    public void setup() {
        super.setup();
        animatedObject = newAnimatedItem();
        Mockito.when(objectFactory.get(Mockito.any())).thenReturn(animatedObject);
    }

    private AnimatedObject newAnimatedItem() {
        AnimatedObject item = new AnimatedObject(newTestSkin());
        item.setRoot(TestItemRoots.newTestRoot("root"));
        item.setType(UnitType.Footman);
        item.setIdentifier(new TextIdentifier("footman"));
        item.setSize(56, 78);
        item.setPosition(12, 34);
        item.setDirection(new Vector2(1, 1));
        item.setAnimation(UnitAnimation.Build);
        item.setSound(UnitSound.Attack, 1);
        return item;
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(AnimatedObject.class)
            .withDeserializedForm(animatedObject)
            .withSerializedResource("/item/animateditem.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(AnimatedObject.class)
            .withMockedTransientFields()
            .excludeTransientFields()
            .verify();
    }
}