/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.hud;

import com.evilbird.engine.common.inject.IdentifiedAssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.hud.HudControl;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

public class HudFactory implements IdentifiedAssetProvider<ItemRoot>
{
    private ItemFactory itemFactory;

    @Inject
    public HudFactory(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    @Override
    public void load() {
    }

    @Override
    public ItemRoot get(Identifier identifier) {
        Validate.isInstanceOf(HudDefinition.class, identifier);
        HudDefinition definition = (HudDefinition)identifier;

        ItemRoot hud = new ItemRoot();
        //hud.setViewport(viewport);

        for (HudControl hudControl: definition.getControls()) {
            hud.addItem(itemFactory.newItem(hudControl));
        }
        return hud;
    }
}