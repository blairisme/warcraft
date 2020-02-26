/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.invade;

import com.evilbird.engine.common.time.Duration;
import com.evilbird.warcraft.object.unit.UnitType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Describes the game state required for an invasion to take place and the type
 * of attackers that will take part in it.
 *
 * @author Blair Butterworth
 */
public class InvasionWave
{
    private int phase;
    private Duration time;
    private Duration interval;
    private boolean repeating;
    private Map<UnitType, Integer> participants;

    /**
     * Constructs a new instance of this class, with no phase, time, interval
     * or participant requirements.
     */
    public InvasionWave() {
        this.phase = 0;
        this.repeating = false;
        this.time = Duration.ZERO;
        this.interval = Duration.ZERO;
        this.participants = new HashMap<>();
    }

    /**
     * Creates a new empty invasion wave, with no phase, time, interval or
     * participant requirements.
     */
    public static InvasionWave invasionWave() {
        return new InvasionWave();
    }

    /**
     * Returns whether the invasion wave occurs continually: its repeats.
     */
    public boolean isRepeating() {
        return repeating;
    }

    /**
     * Returns the player phase that is required in order for the invasion
     * wave to commence.
     */
    public int getPhaseRequirement() {
        return phase;
    }

    /**
     * Returns the world time that is required in order for the invasion
     * wave to commence.
     */
    public Duration getTimeRequirement() {
        return time;
    }

    /**
     * Returns the interval between waves that is required for repeating waves
     * to commence.
     */
    public Duration getRepeatingInterval() {
        return interval;
    }

    /**
     * Returns the type of combatants involved in the invasion wave.
     */
    public Map<UnitType, Integer> getParticipantTypes() {
        return Collections.unmodifiableMap(participants);
    }

    /**
     * Specifies that the invasion wave occurs continually: its repeats.
     */
    public InvasionWave repeating() {
        this.repeating = true;
        return this;
    }

    /**
     * Specifies that the invasion wave requires the given phase in order to
     * commence.
     */
    public InvasionWave requiresPhase(int phase) {
        this.phase = phase;
        return this;
    }

    /**
     * Specifies that the invasion wave requires the given world time in order
     * to commence.
     */
    public InvasionWave requiresTime(Duration time) {
        this.time = time;
        return this;
    }

    /**
     * Used for {@link InvasionWave#repeating repeating} invasion waves, this
     * specifies that the invasion wave requires the given interval between
     * executions to commence. Specifically this value is multiplied by the
     * number phases that have occurred since the waves first execution and is
     * added to the required world time.
     */
    public InvasionWave withInterval(Duration interval) {
        this.interval = interval;
        return this;
    }

    /**
     * Specifies the type of combatants involved in the invasion wave.
     * Subsequent calls to this method will added to previously specified unit
     * requirements.
     */
    public InvasionWave withUnits(UnitType type, int count) {
        participants.put(type, count);
        return this;
    }
}
