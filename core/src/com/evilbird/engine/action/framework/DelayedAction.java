package com.evilbird.engine.action.framework;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.framework.ActionDuration;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class DelayedAction extends Action
{
    private ActionDuration delay;

    public DelayedAction(float time)
    {
        this(new TimeDuration(time));
    }

    public DelayedAction(ActionDuration delay)
    {
        this.delay = delay;
    }

    @Override
    public boolean act(float delta)
    {
        return delay.isComplete(delta);
    }

    @Override
    public void restart()
    {
        delay.restart();
    }
}
