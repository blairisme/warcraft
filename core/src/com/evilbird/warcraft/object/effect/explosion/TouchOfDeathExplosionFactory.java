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
import com.evilbird.warcraft.object.effect.EffectType;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;

/**
 * A factory for the creation of touch of death explosion effects, loading the
 * necessary assets and generating new game objects.
 *
 * @author Blair Butterworth
 */
public class TouchOfDeathExplosionFactory extends EffectFactoryBase
{
    @Inject
    public TouchOfDeathExplosionFactory(Device device) {
        super(device.getAssetStorage(), EffectType.TouchOfDeathExplosion);
    }

    @Override
    public Effect get(Identifier type) {
        Effect result = builder.build();
        result.setType(EffectType.TouchOfDeathExplosion);
        result.setIdentifier(objectIdentifier("TouchOfDeathExplosion", result));
        result.setSize(32, 32);
        return result;
    }
}
