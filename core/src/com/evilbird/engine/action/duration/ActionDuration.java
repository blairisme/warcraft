package com.evilbird.engine.action.duration;

public interface ActionDuration
{
    boolean isComplete(float time);

    void restart();
}
