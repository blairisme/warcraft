/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.construct;

import com.badlogic.gdx.ai.btree.branch.Sequence;

import javax.inject.Inject;

/**
 * @author Blair Butterworth
 */
public class ConstructSequence extends Sequence<ConstructData>
{
    @Inject
    public ConstructSequence(
        SelectBuilder selectBuilder,
        SelectBuilding selectBuilding,
        SelectLocation selectLocation,
        ConstructTask constructTask)
    {
        super(selectBuilder,
            selectBuilding,
            selectLocation,
            constructTask);
    }
}
