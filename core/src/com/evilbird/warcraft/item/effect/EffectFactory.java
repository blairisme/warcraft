/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.effect;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.effect.confirm.ConfirmProvider;

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
    public EffectFactory(ConfirmProvider confirmProvider) {
        addProvider(EffectType.Confirm, confirmProvider);
    }
}
