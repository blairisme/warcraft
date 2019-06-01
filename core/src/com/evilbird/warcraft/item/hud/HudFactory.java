/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.inject.IdentifiedAssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

public class HudFactory implements IdentifiedAssetProvider<Item>
{
    private HudControlFactory controlFactory;

    @Inject
    public HudFactory(HudControlFactory controlFactory) {
        this.controlFactory = controlFactory;
    }

    @Override
    public Item get(Identifier identifier) {
        Validate.isInstanceOf(HudType.class, identifier);
        return get((HudType)identifier);
    }

    private Item get(HudType hudType) {
        ItemGroup hud = new ItemGroup();
        hud.setFillParent(true);
        hud.setTouchable(Touchable.childrenOnly);

        hud.addItem(controlFactory.get(HudControl.ResourcePane));
        hud.addItem(controlFactory.get(HudControl.ControlPane));
        return hud;
    }

    @Override
    public void load() {
        controlFactory.load();
    }
}
