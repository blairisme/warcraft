/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.production.construct;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.behaviour.ai.common.select.SelectFirstSubject;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import javax.inject.Inject;

/**
 * A leaf task that selects an available builder to construct the building
 * contained in the tasks blackboard.
 *
 * @author Blair Butterworth
 */
//TODO: Oil Platforms need to be constructed by an oil tanker
public class SelectBuilder extends SelectFirstSubject<ConstructData>
{
    @Inject
    public SelectBuilder() {
        from(ConstructData::getPlayer);
        into(ConstructData::setBuilder);
        when(SelectBuilder::validBuilder);
    }

    private static boolean validBuilder(GameObject gameObject) {
        if (gameObject instanceof Gatherer) {
            Gatherer gatherer = (Gatherer)gameObject;
            return gatherer.isAlive() && gatherer.getVisible();
        }
        return false;
    }
}
