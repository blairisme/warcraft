package com.evilbird.warcraft.state.hud;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.ItemRoot;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class HudProvider extends IdentifiedAssetProviderSet<ItemRoot>
{
    @Inject
    public HudProvider(HumanHudProvider humanHudProvider, OrcHudProvider orcHudProvider)
    {
        addProvider(HudType.Human, humanHudProvider);
        addProvider(HudType.Orc, orcHudProvider);
    }
}
