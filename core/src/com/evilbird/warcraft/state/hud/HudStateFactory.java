/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.hud;

import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.hud.HudControl;

import javax.inject.Inject;

public class HudStateFactory
{
    private ItemFactory itemFactory;

    @Inject
    public HudStateFactory(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    public ItemRoot get(HudStateIdentifier identifier) {
        ItemRoot hud = new ItemRoot();
        //hud.setViewport(viewport);

        for (HudControl hudControl: identifier.getControls()) {
            hud.addItem(itemFactory.newItem(hudControl));
        }
        return hud;
    }
}