/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.badlogic.gdx.utils.Pool;
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.warcraft.common.WarcraftPreferences;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ProximityAttackTest
{
    private GameObject gameObject;
    private GameObject target;
    private ProximityAttack action;
    private WarcraftPreferences preferences;

    @Before
    public void setup() {
        gameObject = TestItems.newItem("footman");
        target = TestItems.newItem("grunt");
        preferences = Mockito.mock(WarcraftPreferences.class);

        action = new ProximityAttack(preferences);
        action.setSubject(gameObject);
        action.setTarget(target);
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(ProximityAttack.class)
            .withMockedTransientFields(GameObject.class)
            .withMockedType(Pool.class)
            .excludeTransientFields()
            .verify();
    }
}