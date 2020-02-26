/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.invade;

import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.Timepiece;
import com.evilbird.engine.common.time.Duration;
import com.evilbird.warcraft.object.data.player.Player;

import java.util.Arrays;
import java.util.List;

import static com.evilbird.engine.common.time.ChronoUnit.SECONDS;

/**
 * Specifies the order in which an enemy player invades the teams that oppose
 * it.
 *
 * @author Blair Butterworth
 */
public class InvasionOrder
{
    private List<InvasionWave> waves;

    /**
     * Constructs a new instance of this class given a collection of invasion
     * waves that constitute the invasion order.
     */
    public InvasionOrder(InvasionWave ... waves) {
        this.waves = Arrays.asList(waves);
    }

    /**
     * Returns details about the next invasion wave.
     */
    public InvasionWave getNextWave(Player player) {
        int phase = player.getPhase();
        long time = getWorldTime();

        for (InvasionWave wave: waves) {
            if (isValid(wave, phase, time)) {
                return wave;
            }
        }
        return null;
    }

    private boolean isValid(InvasionWave wave, int playerPhase, long worldTime) {
        if (wave.isRepeating()) {
            return isValidRepeating(wave, playerPhase, worldTime);
        } else {
            return isValidNonRepeating(wave, playerPhase, worldTime);
        }
    }

    private boolean isValidNonRepeating(InvasionWave wave, int playerPhase, long worldTime) {
        int wavePhase = wave.getPhaseRequirement();
        long waveTime = getWaveTime(wave);
        return wavePhase == playerPhase && worldTime >= waveTime;
    }

    private boolean isValidRepeating(InvasionWave wave, int playerPhase, long worldTime) {
        int wavePhase = wave.getPhaseRequirement();
        int repeatedPhases = playerPhase - wavePhase;
        long waveTime = getWaveTime(wave) + (repeatedPhases * getWaveInterval(wave));
        return worldTime >= waveTime;
    }

    private long getWorldTime() {
        Timepiece timeService = GdxAI.getTimepiece();
        return (long)timeService.getTime();
    }

    private long getWaveTime(InvasionWave wave) {
        Duration timeRequirement = wave.getTimeRequirement();
        return timeRequirement.get(SECONDS);
    }

    private long getWaveInterval(InvasionWave wave) {
        Duration repeatingInterval = wave.getRepeatingInterval();
        return repeatingInterval.get(SECONDS);
    }
}
