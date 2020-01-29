/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.gather;

import com.badlogic.gdx.ai.btree.branch.Sequence;

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
        SelectGatherer selectGatherer,
        SelectResource selectResource,
        GatherTask gatherTask)
    {
        super(selectGatherer, selectResource, gatherTask);
    }
}
