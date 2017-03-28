package com.evilbird.warcraft.item.effect;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.effect.confirm.ConfirmProvider;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class EffectProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public EffectProvider(ConfirmProvider confirmProvider)
    {
        addProvider(EffectType.Confirm, confirmProvider);
    }
}
