/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.object.GameObject;
import com.evilbird.test.data.item.TestItems;
import org.junit.Before;
import org.mockito.Mockito;

public class ProximityAttackTest
{
    private AttackDamage damage;
    private AttackEvents events;
    private GameObject gameObject;
    private GameObject target;
    private ProximityAttack action;

    @Before
    public void setup() {
        gameObject = TestItems.newItem("footman");
        target = TestItems.newItem("grunt");

        damage = Mockito.mock(AttackDamage.class);
        events = Mockito.mock(AttackEvents.class);
        action = new ProximityAttack(damage, events);
        action.setSubject(gameObject);
        action.setTarget(target);
    }

//    @Test
//    public void equalsTest() {
//        EqualityVerifier.forClass(ProximityAttack.class)
//            .withMockedTransientFields(GameObject.class)
//            .withMockedType(Pool.class)
//            .excludeTransientFields()
//            .verify();
//    }
}