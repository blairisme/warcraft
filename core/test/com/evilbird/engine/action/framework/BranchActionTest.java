/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.function.Predicates;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.action.TestBasicAction;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Instances of this unit test validate the {@link BranchAction} class.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("unchecked")
public class BranchActionTest
{
    private Action trueAction;
    private Action falseAction;
    private Action branchAction;
    private Predicate<Action> predicate;

    @Before
    public void setup() {
        trueAction = new TestBasicAction();
        falseAction = new TestBasicAction();
        predicate = Predicates.accept();
        branchAction = new BranchAction(predicate, trueAction, falseAction);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(BranchAction.class)
            .withDeserializedForm(branchAction)
            .withSerializedResource("/branchaction.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(BranchAction.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }
}
