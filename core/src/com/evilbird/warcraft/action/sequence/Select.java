/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.AudibleAction;
import com.evilbird.engine.action.common.SelectAction;
import com.evilbird.engine.action.framework.EmptyAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Audible;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.item.unit.UnitSound;

import javax.inject.Inject;

/**
 * Instances of this class toggle the selection of a given {@link Item}.
 *
 * @author Blair Butterworth
 */
public class Select implements ActionProvider
{
    @Inject
    public Select() {
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        Item item = context.getItem();
        if (item.getSelectable()) {
            return get(item, !item.getSelected());
        }
        return new EmptyAction();
    }

    public Action get(Item item, boolean selected) {
        Action result = new SelectAction(item, selected);
        if (selected) {
            Action sound = new AudibleAction((Audible)item, UnitSound.Selected);
            result = new ParallelAction(result, sound);
        }
        return result;
    }
}
