/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.conjured;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.conjured.orc.RuneTrapFactory;

import javax.inject.Inject;

/**
 * @author Blair Butterworth
 */
public class ConjuredFactory extends GameFactorySet<Item>
{
    @Inject
    public ConjuredFactory(RuneTrapFactory runeTrapFactory) {
        addProvider(UnitType.RuneTrap, runeTrapFactory);
    }
}
