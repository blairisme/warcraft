package com.evilbird.engine.action.framework.duration;

public interface ActionDuration
{
    boolean isComplete(float time);

    void restart();
}
