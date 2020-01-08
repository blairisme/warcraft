/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.selector;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.selector.building.BuildingSelectorFactory;
import com.evilbird.warcraft.object.selector.target.TargetSelectorFactory;

import javax.inject.Inject;

/**
 * A factory for visual guides used by the user to select items and locations
 * in the game world.
 *
 * @author Blair Butterworth
 */
public class SelectorFactory extends GameFactorySet<GameObject>
{
    @Inject
    public SelectorFactory(
        BuildingSelectorFactory buildingSelectorFactory,
        TargetSelectorFactory targetSelectorFactory)
    {
        addProvider(SelectorType.buildingSelectors(), buildingSelectorFactory);
        addProvider(SelectorType.targetSelectors(), targetSelectorFactory);
    }
}
