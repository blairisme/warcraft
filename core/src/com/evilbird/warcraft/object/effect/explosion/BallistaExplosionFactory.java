/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.effect.explosion;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.effect.Effect;
import com.evilbird.warcraft.object.effect.EffectFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.effect.EffectType.SiegeExplosion;

/**
 * A factory for the creation of ballista explosion effects, loading the
 * necessary assets and generating new game objects.
 *
 * @author Blair Butterworth
 */
public class BallistaExplosionFactory extends EffectFactoryBase
{
    @Inject
    public BallistaExplosionFactory(Device device) {
        super(device.getAssetStorage(), SiegeExplosion);
    }

    @Override
    public Effect get(Identifier type) {
        Effect result = builder.build();
        result.setType(SiegeExplosion);
        result.setIdentifier(objectIdentifier("BallistaExplosion", result));
        result.setSize(48, 48);
        return result;
    }
}
