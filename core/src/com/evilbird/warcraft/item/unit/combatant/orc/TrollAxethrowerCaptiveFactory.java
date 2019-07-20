/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.unit.UnitType.TrollAxethrowerCaptive;

/**
 * Instances of this factory create Troll Axe Throwers Captives, Orcish entry
 * level ranged {@link Combatant Combatants} that have been captured and can be
 * rescued.
 *
 * @author Blair Butterworth
 */
public class TrollAxethrowerCaptiveFactory extends TrollAxethrowerFactory
{
    @Inject
    public TrollAxethrowerCaptiveFactory(Device device) {
        super(device);
    }

    public TrollAxethrowerCaptiveFactory(AssetManager manager) {
        super(manager);
    }

    @Override
    public Combatant get(Identifier type) {
        Combatant result = super.get(type);
        result.setType(TrollAxethrowerCaptive);
        return result;
    }
}

