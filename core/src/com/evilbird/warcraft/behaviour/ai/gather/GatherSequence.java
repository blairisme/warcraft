/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.gather;

import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.evilbird.engine.behaviour.framework.guard.RandomWait;

import javax.inject.Inject;

/**
 * A task sequence that instructs an idle gather to obtain resources from an
 * appropriate resource.
 *
 * @author Blair Butterworth
 */
public class GatherSequence extends Sequence<GatherData>
{
    @Inject
    public GatherSequence(
        GatherTask gather,
        SelectGatherer selectGatherer,
        SelectLocation selectLocation,
        SelectResource selectResource)
    {
        super(evaluationDelay(),
              selectResource,
              selectGatherer,
              selectLocation,
              gather);
    }

    private static Task<GatherData> evaluationDelay() {
        return new RandomWait<GatherData>()
            .waitMinimum(0.25f)
            .waitMaximum(0.5f);
    }
}
