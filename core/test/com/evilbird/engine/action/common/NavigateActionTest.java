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
import com.evilbird.warcraft.action.menu.MenuNavigateAction;
import com.evilbird.warcraft.item.hud.control.actions.ActionPaneLayout;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link MenuNavigateAction} class.
 *
 * @author Blair Butterworth
 */
public class NavigateActionTest
{
    private MenuNavigateAction action;

    @Before
    public void setup() {
        action = new MenuNavigateAction(ActionPaneLayout.SimpleBuildings, ActionTarget.Item);
        action.setItem(TestItems.newItem("navigateaction"));
    }

//    @Test
//    public void serializeTest() throws IOException {
//        SerializationVerifier.forClass(MenuNavigateAction.class)
//            .withDeserializedForm(action)
//            .withSerializedResource("/action/common/navigateaction.json")
//            .verify();
//    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(MenuNavigateAction.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }
}