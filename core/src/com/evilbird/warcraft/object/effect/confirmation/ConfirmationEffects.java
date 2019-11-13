/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.effect.confirmation;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.effect.Effect;
import com.evilbird.warcraft.object.effect.EffectType;

import javax.inject.Inject;

/**
 * A factory for the creation of confirmation effects.
 *
 * @author Blair Butterworth
 */
public class ConfirmationEffects extends GameFactorySet<Effect>
{
    @Inject
    public ConfirmationEffects(
        AttackEffectFactory attackEffectFactory,
        ConfirmEffectFactory confirmEffectFactory)
    {
        addProvider(EffectType.Attack, attackEffectFactory);
        addProvider(EffectType.Confirm, confirmEffectFactory);
    }
}
