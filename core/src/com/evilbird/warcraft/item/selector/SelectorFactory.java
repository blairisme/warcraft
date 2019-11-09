/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.selector;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.selector.building.BuildingSelectorFactory;
import com.evilbird.warcraft.item.selector.target.TargetSelectorFactory;

import javax.inject.Inject;

/**
 * @author Blair Butterworth
 */
public class SelectorFactory extends GameFactorySet<Item>
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
