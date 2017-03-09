package com.evilbird.warcraft.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ClearAction;
import com.evilbird.engine.action.SequenceAction;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class StopActionProvider implements ActionProvider
{
    private AnimateActionProvider animateActionProvider;

    @Inject
    public StopActionProvider(AnimateActionProvider animateActionProvider)
    {
        this.animateActionProvider = animateActionProvider;
    }

    @Override
    public Action get(Item item, Item target, UserInput input)
    {
        Action clearAction = new ClearAction(item);
        Action idleAnimation = animateActionProvider.get(item, new Identifier("Idle"));
        return new SequenceAction(clearAction, idleAnimation);
    }
}
