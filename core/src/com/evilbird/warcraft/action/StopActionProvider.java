package com.evilbird.warcraft.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ClearAction;
import com.evilbird.engine.action.SequenceAction;
import com.evilbird.engine.action.replacement.AnimateAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.warcraft.item.unit.UnitAnimation;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class StopActionProvider implements ActionProvider
{
    @Inject
    public StopActionProvider()
    {
    }

    @Override
    public Action get(ActionType action, Item item, Item target, UserInput input)
    {
        Action clearAction = new ClearAction(item);
        Action idleAnimation = new AnimateAction((Animated)item, UnitAnimation.Idle);
        return new SequenceAction(idleAnimation, clearAction);
    }
}
