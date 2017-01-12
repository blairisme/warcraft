package com.evilbird.warcraft.action.duration;

public class InstantDuration implements ActionDuration
{
    @Override
    public boolean isComplete(float time)
    {
        return true;
    }

    @Override
    public void restart()
    {
    }
}
