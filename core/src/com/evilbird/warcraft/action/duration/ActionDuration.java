package com.evilbird.warcraft.action.duration;

public interface ActionDuration
{
    boolean isComplete(float time);

    void restart();
}
