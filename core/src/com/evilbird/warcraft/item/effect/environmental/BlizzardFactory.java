/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.effect.environmental;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.effect.Effect;
import com.evilbird.warcraft.item.effect.EffectFactoryBase;
import com.evilbird.warcraft.item.effect.EffectType;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;

/**
 * A factory for the creation of blizzard effects, loading the necessary
 * assets and generating new game objects.
 *
 * @author Blair Butterworth
 */
public class BlizzardFactory extends EffectFactoryBase
{
    @Inject
    public BlizzardFactory(Device device) {
        super(device.getAssetStorage(), EffectType.Blizzard);
    }

    @Override
    public Effect get(Identifier type) {
        Effect result = builder.build();
        result.setType(EffectType.Blizzard);
        result.setIdentifier(objectIdentifier("Blizzard", result));
        result.setSize(48, 48);
        return result;
    }
}
