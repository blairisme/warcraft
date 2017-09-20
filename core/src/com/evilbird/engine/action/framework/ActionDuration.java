package com.evilbird.engine.action.framework;

public interface ActionDuration
{
    boolean isComplete(float time);

    void restart();
}
