package com.evilbird.warcraft.action.modifier;

public interface ActionModifier
{
    /**
     * Modifies the given value based on the given time.
     *
     * @param value the Object to modify.
     * @param time the time in seconds since the last modification.
     * @return true if the action is complete, otherwise false.
     */
    Object modify(Object value, float time);

    void restart();
}
