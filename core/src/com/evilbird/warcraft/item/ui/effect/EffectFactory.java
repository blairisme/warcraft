/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.effect;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.ui.effect.confirm.ConfirmFactory;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Item Items} that display an effect,
 * visual feedback that assists the user.
 *
 * @author Blair Butterworth
 */
public class EffectFactory extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public EffectFactory(ConfirmFactory confirmFactory) {
        addProvider(EffectType.Confirm, confirmFactory);
    }
}
