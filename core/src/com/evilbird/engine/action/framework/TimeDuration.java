package com.evilbird.engine.action.framework;

public class TimeDuration implements ActionDuration
{
    private float duration;
    private float total;

    /**
     * Creates a new instance of this class given the length of the duration.
     *
     * @param duration the length of the duration specified in seconds.
     */
    public TimeDuration(float duration)
    {
        this.duration = duration;
        restart();
    }

    @Override
    public boolean isComplete(float time)
    {
        total += time;
        return total >= duration;
    }

    @Override
    public void restart()
    {
        this.total = 0;
    }
}
