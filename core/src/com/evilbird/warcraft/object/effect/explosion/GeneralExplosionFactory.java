/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
 * A factory for the creation of explosion effects, loading the necessary
 * assets and generating new game objects.
 *
 * @author Blair Butterworth
 */
public class GeneralExplosionFactory extends EffectFactoryBase
{
    @Inject
    public GeneralExplosionFactory(Device device) {
        super(device.getAssetStorage(), EffectType.Explosion);
    }

    @Override
    public Effect get(Identifier type) {
        Effect result = builder.build();
        result.setType(EffectType.Explosion);
        result.setIdentifier(objectIdentifier("Explosion", result));
        result.setSize(64, 64);
        return result;
    }
}
