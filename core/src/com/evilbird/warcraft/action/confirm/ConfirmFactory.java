/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.confirm;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.utilities.InjectedPool;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.ActionProvider;

import javax.inject.Inject;

public class ConfirmFactory implements ActionProvider
{
    private InjectedPool<ConfirmAction> pool;

    @Inject
    public ConfirmFactory(InjectedPool<ConfirmAction> pool) {
        this.pool = pool;
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        switch ((ConfirmActions)action) {
            case ConfirmLocation: return getConfirmLocationAction(context);
            case ConfirmTarget: return getConfirmTargetAction(context);
            default: throw new UnsupportedOperationException();
        }
    }

    private Action getConfirmLocationAction(ActionContext context) {
        Item item = context.getItem();
        ItemRoot root = item.getRoot();

        UserInput input = context.getInput();
        Vector2 destination = root.unproject(input.getPosition());

        ConfirmAction confirm = pool.obtain();
        confirm.setLocation(destination, root);
        return confirm;
    }

    private Action getConfirmTargetAction(ActionContext context) {
        Item item = context.getItem();
        ItemRoot root = item.getRoot();

        ConfirmAction confirm = pool.obtain();
        confirm.setLocation(item, root);
        return confirm;

        //Action sound = new AudibleAction((Audible)attacker, UnitSound.Acknowledge);
        //return new ParallelAction(effect, sound);
    }
}
