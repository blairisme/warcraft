/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.testcase;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.common.reflect.TypeRegistry;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.utils.TestUtils;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.type.WarcraftTypeRegistry;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Instances of this test case provide common validation for {@link Action
 * Actions}.
 *
 * @author Blair Butterworth
 */
public abstract class ActionTestCase extends GameTestCase
{
    //protected GameObjectContainer root;
    protected GameObject gameObject;
    protected GameObject target;
    protected UserInput cause;
    protected Action action;
    protected Player player;
    protected TypeRegistry types;

    @Before
    public void setup() {
        super.setup();

        types = new WarcraftTypeRegistry();
        gameObject = newItem();
        target = newTarget();
        cause = newCause();
        player = (Player)gameObject.getParent();

        action = newAction();
        action.setSubject(gameObject);
        action.setTarget(target);
        action.setCause(cause);

        respondWithAction(action);
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(Action.class)
            .withMockedTransientFields(GameObject.class)
            .excludeTransientFields()
            .verify();
    }

    @Test
    public void serializeTest() throws IOException {
        Enum id = newIdentifier();
        String actionId = id.name();
        String actionType = types.getName(id.getClass());

        String serialized = TestUtils.readResource("/test/actiontestcase.json");
        serialized = serialized.replace("${Action}", actionId);
        serialized = serialized.replace("${ActionType}", actionType);

        SerializationVerifier.forClass(Action.class)
            .withDeserializedForm(action)
            .withSerializedForm(serialized)
            .verify();
    }

    @Test
    public void actTest() {
        action.act(1);
    }

    protected abstract Action newAction();

    protected abstract Enum newIdentifier();

    protected GameObject newItem() {
        return TestCombatants.newTestCombatant(new TextIdentifier("item"), UnitType.Footman);
    }

    protected GameObject newTarget() {
        return TestCombatants.newTestCombatant(new TextIdentifier("target"), UnitType.Grunt);
    }

    protected UserInput newCause() {
        return new UserInput(UserInputType.Action, Vector2.Zero, 1);
    }
}