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
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InvasionWave
{
    private Duration time;
    private Duration interval;
    private List<Pair<UnitType, Integer>> participants;

    public InvasionWave(Duration time) {
        this.time = time;
        this.interval = Duration.ZERO;
        this.participants = new ArrayList<>();
    }

    public static InvasionWave invadeAfter(Duration time) {
        return new InvasionWave(time);
    }

    public List<Pair<UnitType, Integer>> getParticipantTypes() {
        return Collections.unmodifiableList(participants);
    }

    public InvasionWave repeatingAtIntervals(Duration interval) {
        this.interval = interval;
        return this;
    }

    public InvasionWave withUnits(UnitType type, int count) {
        participants.add(Pair.of(type, count));
        return this;
    }
}
