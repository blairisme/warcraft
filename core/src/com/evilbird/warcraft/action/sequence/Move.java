/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.ReplacementAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.component.AnimatedMoveAction;
import com.evilbird.warcraft.action.component.ConfirmAction;

import javax.inject.Inject;

/**
 * Instances of this class instruct a given {@link Item} to move to the given
 * target.
 *
 * @author Blair Butterworth
 */
public class Move implements ActionProvider
{
    private ItemFactory itemFactory;

    @Inject
    public Move(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        Item item = context.getItem();
        ItemRoot root = item.getRoot();

        UserInput input = context.getInput();
        Vector2 destination = root.unproject(input.getPosition());

        Action confirm = new ConfirmAction(itemFactory, item.getParent(), destination);
        Action move = new AnimatedMoveAction(item, destination);
        Action confirmedMove = new ParallelAction(confirm, move);

        return new ReplacementAction(item, confirmedMove);
    }
}
