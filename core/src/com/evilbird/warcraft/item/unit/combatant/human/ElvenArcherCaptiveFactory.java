/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.human;

import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.unit.UnitType.ElvenArcherCaptive;

/**
 * Instances of this factory create Elven Archers Captive, an Elven Archer
 * specialization used in the second human level.
 *
 * @author Blair Butterworth
 */
public class ElvenArcherCaptiveFactory extends ElvenArcherFactory
{
    @Inject
    public ElvenArcherCaptiveFactory(Device device) {
        super(device);
    }

    @Override
    public Item get() {
        Item result = super.get();
        result.setType(ElvenArcherCaptive);
        return result;
    }
}
