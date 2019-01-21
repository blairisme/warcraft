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

public class EffectProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public EffectProvider(ConfirmProvider confirmProvider)
    {
        addProvider(EffectType.Confirm, confirmProvider);
    }
}
