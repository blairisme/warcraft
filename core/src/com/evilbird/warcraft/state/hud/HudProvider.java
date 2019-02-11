/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.hud;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.state.hud.human.HumanHudProvider;
import com.evilbird.warcraft.state.hud.orc.OrcHudProvider;

import javax.inject.Inject;

public class HudProvider extends IdentifiedAssetProviderSet<ItemRoot>
{
    @Inject
    public HudProvider(HumanHudProvider humanHudProvider, OrcHudProvider orcHudProvider) {
        addProvider(HudType.Human, humanHudProvider);
        addProvider(HudType.Orc, orcHudProvider);
    }
}
