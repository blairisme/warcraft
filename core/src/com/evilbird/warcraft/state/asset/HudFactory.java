/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.asset;

import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.hud.HudControl;

import javax.inject.Inject;

public class HudFactory
{
    private ItemFactory itemFactory;

    @Inject
    public HudFactory(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    public ItemRoot get(HudDefinition definition) {
        ItemRoot hud = new ItemRoot();
        //hud.setViewport(viewport);

        for (HudControl hudControl: definition.getControls()) {
            hud.addItem(itemFactory.newItem(hudControl));
        }
        return hud;
    }
}