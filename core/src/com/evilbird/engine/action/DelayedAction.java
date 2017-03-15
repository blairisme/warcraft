package com.evilbird.engine.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.duration.ActionDuration;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class DelayedAction extends Action
{
    private Action action;
    private ActionDuration delay;
    private boolean invoked;

    public DelayedAction(Action action, ActionDuration delay)
    {
        this.action = action;
        this.delay = delay;
        this.invoked = false;
    }

    @Override
    public boolean act(float delta)
    {
        if (! invoked){
            invoked = action.act(delta);
        }
        return delay.isComplete(delta);
    }

    @Override
    public void restart()
    {
        action.restart();
        delay.restart();
        invoked = false;
    }
}
