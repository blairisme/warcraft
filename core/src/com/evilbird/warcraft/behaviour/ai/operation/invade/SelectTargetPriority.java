/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.invade;

import com.evilbird.engine.object.GameObject;

import java.util.Comparator;

/**
 * @author Blair Butterworth
 */
public class SelectTargetPriority implements Comparator<GameObject>
{
    @Override
    public int compare(GameObject o1, GameObject o2) {
        //TODO: buildings first, prioritising command centres
        //TODO: units second, prioritising gatherers
        return 0;
    }
}
