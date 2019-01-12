package com.evilbird.warcraft.action.component;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.action.common.DisableAction;
import com.evilbird.engine.action.common.RemoveAction;
import com.evilbird.engine.action.common.SelectAction;
import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.warcraft.item.unit.UnitAnimation;

public class DieAction extends Action
{
    private Action delegate;

    public DieAction(Item item)
    {
        Action deselect = new SelectAction(item, false);
        Action disable = new DisableAction(item, false);

        Action dieAnimation = new AnimateAction((Animated)item, UnitAnimation.Die);
        Action dieWait = new DelayedAction(new TimeDuration(0.5f));
        Action dieSequence = new SequenceAction(dieAnimation, dieWait);
        Action die = new ParallelAction(deselect, disable, dieSequence);

        Action decomposeAnimation = new AnimateAction((Animated)item, UnitAnimation.Decompose);
        Action decomposeWait = new DelayedAction(new TimeDuration(10f));
        Action decompose = new SequenceAction(decomposeAnimation, decomposeWait);

        Action remove = new RemoveAction(item);
        delegate = new SequenceAction(die, decompose, remove);
    }

    @Override
    public boolean act(float delta)
    {
        return delegate.act(delta);
    }
}
