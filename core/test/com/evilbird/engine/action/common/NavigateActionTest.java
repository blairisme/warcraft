/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.warcraft.item.hud.control.actions.ActionPaneLayout;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link NavigateAction} class.
 *
 * @author Blair Butterworth
 */
public class NavigateActionTest
{
    private NavigateAction action;

    @Before
    public void setup() {
        action = new NavigateAction(ActionPaneLayout.SimpleBuildings, ActionTarget.Item);
        action.setItem(TestItems.newItem("navigateaction"));
    }

//    @Test
//    public void serializeTest() throws IOException {
//        SerializationVerifier.forClass(NavigateAction.class)
//            .withDeserializedForm(action)
//            .withSerializedResource("/action/common/navigateaction.json")
//            .verify();
//    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(NavigateAction.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }
}