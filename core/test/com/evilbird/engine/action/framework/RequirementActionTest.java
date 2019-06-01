/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.item.Item;
import com.evilbird.test.data.action.TestBasicAction;
import com.evilbird.test.data.lang.TestPredicate;
import com.evilbird.test.verifier.EqualityVerifier;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link RequirementAction} class.
 *
 * @author Blair Butterworth
 */
public class RequirementActionTest
{
    private TestBasicAction delegate;
    private TestPredicate predicate;
    private RequirementAction action;

    @Before
    public void setup() {
        delegate = new TestBasicAction();
        predicate = new TestPredicate();
        action = new RequirementAction(delegate, predicate);
    }

//    @Test
//    public void serializeTest() throws IOException {
//        SerializationVerifier.forClass(RequirementAction.class)
//                .withDeserializedForm(action)
//                .withSerializedResource("/action/framework/requirementaction.json")
//                .verify();
//    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(RequirementAction.class)
                .withMockedTransientFields(Item.class)
                .excludeTransientFields()
                .verify();
    }

    @Test
    public void actTest() {

    }
}